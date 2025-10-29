package mvc_jdbc_test.view;

import java.util.Scanner;

public class MainView {
    public void showHomeView() {
        System.out.println("\n###### Shop DB ######\n");
        System.out.println("--- 모드 선택 ---");
        System.out.println("0. 프로그램 종료");
        System.out.println("1. 데이터 조회");
        System.out.println("2. 데이터 추가");
        System.out.println("3. 데이터 수정");
        System.out.println("4. 데이터 삭제");
    }

    public void showMainView(String mode) {
        System.out.printf("\n###### %s 모드 ######\n\n", mode);
        System.out.printf("--- %s DB 선택 ---\n", mode);
        System.out.println("0. 뒤로가기");
        System.out.println("1. 고객 DB");
        System.out.println("2. 제품 DB");
        System.out.println("3. 주문 DB");
    }

    public static void inputEnter(Scanner sc) {
        System.out.println("\nEnter를 눌러 돌아가기");
        sc.nextLine();
    }

    public int inputAnswer(Scanner sc, int min, int max) {
        int answer;

        while (true) {
            try {
                System.out.print("\n입력 : ");
                String line = sc.nextLine();
                answer = Integer.parseInt(line);
                if (answer < min || answer > max) {
                    System.out.printf("%d~%d의 숫자를 입력해주세요.", min, max);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
            }
        }
        return answer;
    }

    public boolean askYorN(Scanner sc, String yesText, String yesChar, String noText, String noChar) {
        String answer;
        while (true) {
            System.out.printf("\n%s : %s\n%s : %s\n", yesText, yesChar, noText, noChar);
            System.out.print("\n입력 : ");
            answer = sc.nextLine();

            if (answer.equalsIgnoreCase(yesChar)) return true;
            else if (answer.equalsIgnoreCase(noChar)) return false;
            else System.out.println("잘못된 입력입니다. 다시 입력해주세요.\n");
        }
    }
}
