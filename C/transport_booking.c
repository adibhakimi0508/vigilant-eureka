#include <stdio.h>
#include <string.h>

#define MAX_VEHICLES 10
#define MAX_APPLICANTS 100

typedef struct {
    int applicantId;
    char name[100];
    char phone[20];
    char date[20];
    char vehicleNeeded[10];
    char reason[100];
    double distance;
    int status; // 0 = pending, 1 = passed, -1 = failed
} Applicant;

typedef struct {
    int vehicleId;
    char type[10];
    int available;
    double fuelTank;
    double maxDistance;
    char brand[20];
    char plate[20];
    char color[20];
} Vehicle;

Vehicle vehicles[MAX_VEHICLES] = {
    {1001, "car", 1, 45.0, 400.0, "Perodua", "ABC1234", "Red"},
    {1002, "van", 1, 70.0, 500.0, "Nissan", "XYZ5678", "White"},
    {1003, "bus", 1, 300.0, 1000.0, "Volvo", "BUS1010", "Blue"},
    {1004, "car", 1, 40.0, 350.0, "Honda", "DEF2222", "Black"},
    {1005, "van", 1, 80.0, 600.0, "Toyota", "VAN3333", "Silver"},
    {1006, "bus", 1, 320.0, 1050.0, "Mercedes", "BUS2020", "Green"},
    {1007, "car", 1, 50.0, 420.0, "Toyota", "JKL8888", "Yellow"},
    {1008, "van", 1, 75.0, 550.0, "Isuzu", "VAN4444", "Grey"},
    {1009, "bus", 1, 310.0, 980.0, "Scania", "BUS3030", "Orange"},
    {1010, "car", 1, 48.0, 390.0, "Proton", "LMN9999", "White"}
};

Applicant applicants[MAX_APPLICANTS];
int applicantCount = 0;
int nextApplicantId = 20001;

void submitApplication() {
    Applicant a;
    a.applicantId = nextApplicantId++;
    a.status = 0;

    printf("Enter full name: ");
    fgets(a.name, sizeof(a.name), stdin); strtok(a.name, "\n");

    printf("Enter contact number: ");
    fgets(a.phone, sizeof(a.phone), stdin); strtok(a.phone, "\n");

    printf("Enter application date (YYYY-MM-DD): ");
    fgets(a.date, sizeof(a.date), stdin); strtok(a.date, "\n");

    printf("Enter vehicle type needed (car/van/bus): ");
    fgets(a.vehicleNeeded, sizeof(a.vehicleNeeded), stdin); strtok(a.vehicleNeeded, "\n");

    printf("Enter reason for usage: ");
    fgets(a.reason, sizeof(a.reason), stdin); strtok(a.reason, "\n");

    printf("Enter estimated distance (km): ");
    scanf("%lf", &a.distance);
    getchar(); // clear newline

    applicants[applicantCount++] = a;
    printf("Application submitted successfully. Your ID is %d\n", a.applicantId);
}

void viewStatus() {
    char name[100];
    int i, found = 0;
    printf("Enter your full name: ");
    fgets(name, sizeof(name), stdin); strtok(name, "\n");

    for (i = 0; i < applicantCount; i++) {
        if (strcmp(applicants[i].name, name) == 0) {
            found = 1;
            if (applicants[i].status == 1) {
                printf("Application Status: PASSED\n");
            } else if (applicants[i].status == -1) {
                printf("Application Status: FAILED\n");
            } else {
                printf("Application Status: PENDING\n");
            }
            break;
        }
    }
    if (!found) {
        printf("No application submitted.\n");
    }
}

void viewAvailableVehicles() {
    int i, available = 0;
    printf("Available Vehicles:\n");
    for (i = 0; i < MAX_VEHICLES; i++) {
        if (vehicles[i].available) {
            printf("%d, %s, %s, %s\n", vehicles[i].vehicleId, vehicles[i].type, vehicles[i].brand, vehicles[i].plate);
            available++;
        }
    }
    if (available == 0) {
        printf("No vehicles available.\n");
    }
}

void processApplications() {
    int i, j;
    for (i = 0; i < applicantCount; i++) {
        if (applicants[i].status != 0) continue;

        printf("\nApplicant %d: %s requesting a %s\n", applicants[i].applicantId, applicants[i].name, applicants[i].vehicleNeeded);
        printf("Enter decision (pass/fail): ");
        char decision[10];
        fgets(decision, sizeof(decision), stdin); strtok(decision, "\n");

        if (strcmp(decision, "pass") == 0) {
            int assigned = 0;
            for (j = 0; j < MAX_VEHICLES; j++) {
                if (vehicles[j].available && strcmp(vehicles[j].type, applicants[i].vehicleNeeded) == 0) {
                    vehicles[j].available = 0;
                    applicants[i].status = 1;
                    printf("Application Approved. Vehicle %d assigned.\n", vehicles[j].vehicleId);
                    assigned = 1;
                    break;
                }
            }
            if (!assigned) {
                printf("No available vehicle of requested type.\n");
                applicants[i].status = -1;
            }
        } else {
            applicants[i].status = -1;
            printf("Application Rejected.\n");
        }
    }

    int allUnavailable = 1;
    for (i = 0; i < MAX_VEHICLES; i++) {
        if (vehicles[i].available) {
            allUnavailable = 0;
            break;
        }
    }
    if (allUnavailable) {
        printf("All vehicles are now unavailable. Returning to Admin Menu.\n");
    }
}

int main() {
    int choice, appChoice, adminChoice;
    while (1) {
        printf("\n===== MAIN MENU =====\n");
        printf("1. Applicant\n");
        printf("2. Admin\n");
        printf("0. Exit\n");
        printf("Enter your role: ");
        scanf("%d", &choice);
        getchar();

        if (choice == 1) {
            printf("\n--- Applicant Menu ---\n1. Submit Application\n2. View Status\n0. Back\nChoose: ");
            scanf("%d", &appChoice); getchar();
            if (appChoice == 1) submitApplication();
            else if (appChoice == 2) viewStatus();
        } else if (choice == 2) {
            printf("\n--- Admin Menu ---\n1. Process Applications\n2. View Available Vehicles\n0. Back\nChoose: ");
            scanf("%d", &adminChoice); getchar();
            if (adminChoice == 1) processApplications();
            else if (adminChoice == 2) viewAvailableVehicles();
        } else if (choice == 0) {
            printf("Exiting program.\n");
            break;
        } else {
            printf("Invalid choice.\n");
        }
    }
    return 0;
}

