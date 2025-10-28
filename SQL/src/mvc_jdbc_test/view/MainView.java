package mvc_jdbc_test.view;

import java.util.Scanner;

public class MainView {
    public void showMainView() {
        System.out.println("\n###### Shop DB ######\n");
        System.out.println("--- 모드 선택 ---");
        System.out.println("0. 프로그램 종료");
        System.out.println("1. 데이터 조회");
        System.out.println("2. 데이터 수정");
        System.out.println("3. 데이터 삭제");
    }

    public void showQueryView() {
        System.out.println("\n###### 조회 모드 ######\n");
        System.out.println("--- 조회 DB 선택 ---");
        System.out.println("1. 고객 DB");
        System.out.println("2. 제품 DB");
        System.out.println("3. 주문 DB");
    }

    public void showInsertView() {

    }

    public void showUpdateView() {

    }

    public void showDeleteView() {

    }

    public int inputAnswer(Scanner sc, int min, int max) {
        int answer;

        while (true) {
            try {
                System.out.print("입력 : ");
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
}
