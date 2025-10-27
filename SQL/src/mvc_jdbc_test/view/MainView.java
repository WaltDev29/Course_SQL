package mvc_jdbc_test.view;

import java.util.Scanner;

public class MainView {
    public void showMainView() {
        System.out.println("###### Shop DB ######\n");
        System.out.println("--- 모드 선택 ---");
        System.out.println("1. 데이터 조회");
        System.out.println("2. 데이터 수정");
        System.out.println("3. 데이터 삭제");
    }

    public void showQueryView() {

    }

    public void showInsertView() {

    }

    public void showUpdateView() {

    }

    public void showDeleteView() {

    }

    public int inputAnswer(Scanner sc, int range) {
        int answer;

        while (true) {
            try {
                System.out.print("입력 : ");
                String line = sc.nextLine();
                answer = Integer.parseInt(line);
                // 여기 구현해야 함.
//                if (answer <= range) break;
                break;
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
            }
        }
        return answer;
    }
}
