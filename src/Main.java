import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> surnameList = new ArrayList<>();
        ArrayList<Worker> workersFromInput = new ArrayList<>();
        ArrayList<Worker> workersSorted = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Заповніть дані для п'яти працівників.");
        for (int i = 0; i < 5; i++) {

            Worker worker = new Worker();
            System.out.println("Для працівник №" + (i+1) + " введіть" );

            System.out.println("- прізвище та ініціали:");
            String surnameAndInitials = scanner.nextLine();
            worker.surnameAndInitials = surnameAndInitials;
            surnameList.add(surnameAndInitials);

            System.out.println("- назву посади:");
            worker.jobTitle = scanner.nextLine();

            System.out.println("- рік надходження на роботу:");
            int yearOfEmployment;
            boolean throwException;
            do {
                yearOfEmployment = scanner.nextInt();
                throwException = false;
                try {
                    if (yearOfEmployment > Year.now().getValue()) {
                        throw new Exception("Вказана дата більше за поточну");
                    }
                    if (yearOfEmployment < Year.now().getValue() - 42) {
                        throw new Exception("У працівника стаж не повинен бути більше 42 років");
                    }
                }
                catch (Exception e) {
                    System.out.println(e.getMessage() + ". Виправте");
                    throwException = true;
                }
            } while (throwException);

            worker.yearOfEmployment = yearOfEmployment;
            workersFromInput.add(worker);

            scanner.nextLine();//перехід вичитування з попереднього int на line
        }

        System.out.println("Сформований перелік працівників:");
        for (Worker worker : workersFromInput) {
            System.out.println(worker.surnameAndInitials);
            System.out.println(worker.jobTitle);
            System.out.println(worker.yearOfEmployment);
        }

        Collections.sort(surnameList);
        for (String surnameSorted : surnameList) {
            for (Worker worker : workersFromInput) {
                if (worker.surnameAndInitials == surnameSorted)
                    workersSorted.add(worker);
            }
        }

        System.out.println("Відсортований перелік працівників:");
        for (Worker worker : workersSorted) {
            System.out.println(worker.surnameAndInitials);
            System.out.println(worker.jobTitle);
            System.out.println(worker.yearOfEmployment);
        }

        System.out.println("Введіть мінімальний стаж роботи працівника в роках:");
        int experienceMIN = scanner.nextInt();
        for (Worker worker : workersSorted) {
            int experience = Year.now().getValue() - worker.yearOfEmployment;
            if (experience > experienceMIN)
                System.out.println(worker.surnameAndInitials);
        }
    }
}