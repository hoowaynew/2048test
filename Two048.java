
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Two048 {

    // ������飬��¼�����ֺͿհ����
    private HashSet<String> hashSet;
    // ��ʼ������
    private String[] init = {"2", "4"};
    private Random random = new Random();

    public Two048() {
        hashSet = new HashSet<>();
        for (int i = 0; i < 16; i++) {
            hashSet.add(String.valueOf(i));
        }
    }

    // 1.�������
    public String generateForm(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        String cell1 = "------------------------------------\r\n";
        String cell2 = "|--%4s--|-%4s--|--%4s--|--%4s--|\r\n";
        String cell3 = "------------------------------------\r\n";
        String cell4 = "|--%4s--|-%4s--|--%4s--|--%4s--|\r\n";
        String cell5 = "------------------------------------\r\n";
        String cell6 = "|--%4s--|-%4s--|--%4s--|--%4s--|\r\n";
        String cell7 = "------------------------------------\r\n";
        String cell8 = "|--%4s--|-%4s--|--%4s--|--%4s--|\r\n";
        String cell9 = "------------------------------------\r\n";
        sb.append(cell1);
        sb.append(cell2);
        sb.append(cell3);
        sb.append(cell4);
        sb.append(cell5);
        sb.append(cell6);
        sb.append(cell7);
        sb.append(cell8);
        sb.append(cell9);
        String cell = sb.toString();

        return String.format(cell, strArr[0], strArr[1], strArr[2], strArr[3], strArr[4], strArr[5], strArr[6], strArr[7], strArr[8],
                strArr[9], strArr[10], strArr[11], strArr[12], strArr[13], strArr[14], strArr[15]);
    }

    // 2.���������
    public void generateNumber(String[] strArr) {

        int strIndex = random.nextInt(16);

        for (int i = 0; i < 2; i++) {
            int initNum = random.nextInt(2);
            // ���ɱ���пհ�λ������
            if (hashSet.size() < 1) {
                System.out.println("Game Over...");
                System.exit(1);
            }
            while (!hashSet.contains(String.valueOf(strIndex))) {
                strIndex = random.nextInt(16);
            }
            System.out.println(strIndex);
            hashSet.remove(String.valueOf(strIndex));
            strArr[strIndex] = String.valueOf(init[initNum]);
        }
    }

    // 2.1 ����hashset���ϣ��Ա����������������
    private void updateForm(String[] arr) {
        System.out.println(Arrays.toString(arr));
        System.out.println(hashSet);
        hashSet.clear();
        for (int i = 0; i < arr.length; i++) {
            if (" ".equals(arr[i])) {
                hashSet.add(String.valueOf(i));
            }
        }
        System.out.println(hashSet);
    }

    // 3.game����
    public void play(String order, String[] strArr) {
        switch (order) {
            case "w":
                up(strArr);
                break;
            case "s":
                down(strArr);
                break;
            case "a":
                left(strArr);
                break;
            case "d":
                right(strArr);
                break;
            default:
                System.exit(0);
        }
    }

    // 4.��������ʵ��
    private void up(String[] strArr) {
        upMove(strArr); // ��ֹ�������Ԫ��֮������пհ׵����޷���ӣ��������ƶ�Ԫ���ٽ��е���
        for (int i = 4; i < strArr.length; i++) {
            // �ж�������֮����ֵ�Ƿ����
            if (strArr[i].equals(strArr[i - 4]) && !strArr[i].equals(" ")) {
                int num = Integer.parseInt(strArr[i - 4].trim());
                num += Integer.parseInt(strArr[i].trim());
                strArr[i - 4] = num + "";
                strArr[i] = " ";
            }
        }
        upMove(strArr);
        updateForm(strArr);
//            System.out.println(generateForm(strArr));
    }

    private void down(String[] strArr) {
        downMove(strArr); // ��ֹ�������Ԫ��֮������пհ׵����޷���ӣ��������ƶ�Ԫ���ٽ��е���
        for (int i = strArr.length - 1; i > 3; i--) {
            // �ж�������֮����ֵ�Ƿ����
            if (strArr[i].equals(strArr[i - 4]) && !strArr[i].equals(" ")) {
                int num = Integer.parseInt(strArr[i].trim());
                num += Integer.parseInt(strArr[i - 4].trim());
                strArr[i] = num + "";
                strArr[i - 4] = " ";
            }
        }
        downMove(strArr);
        updateForm(strArr);
//            System.out.println(generateForm(strArr));
    }

    private void left(String[] strArr) {
        leftMove(strArr); // ��ֹ�������Ԫ��֮������пհ׵����޷���ӣ��������ƶ�Ԫ���ٽ��е���
        for (int i = 1; i < 4; i++) {
            for (int j = i; j < strArr.length; j = j + 4) {
                // �ж�������֮����ֵ�Ƿ����
                if (strArr[j - 1].equals(strArr[j]) && !strArr[j - 1].equals(" ")) {
                    int num = Integer.parseInt(strArr[j - 1].trim());
                    num += Integer.parseInt(strArr[j].trim());
                    strArr[j - 1] = num + "";
                    strArr[j] = " ";
                }
            }
        }
        leftMove(strArr);
        updateForm(strArr);
//        System.out.println(generateForm(strArr) + "left\n");
    }

    private void right(String[] strArr) {
        rightMove(strArr); // ��ֹ�������Ԫ��֮������пհ׵����޷���ӣ��������ƶ�Ԫ���ٽ��е���
        for (int i = 3; i > 0; i--) {
            for (int j = i; j < strArr.length; j = j + 4) {
                // �ж�������֮����ֵ�Ƿ����
                if (strArr[j].equals(strArr[j - 1]) && !strArr[j].equals(" ")) {
                    int num = Integer.parseInt(strArr[j - 1].trim());
                    num += Integer.parseInt(strArr[j].trim());
                    strArr[j - 1] = num + "";
                    strArr[j] = " ";
                }
            }
        }
        rightMove(strArr);
        updateForm(strArr);
//        System.out.println(generateForm(strArr) + "right\n");
    }


    // 5.Ԫ���ƶ�
    private void upMove(String[] strArr) {
        for (int i = 0; i < strArr.length - 4; i++) {
            for (int j = 0; j < strArr.length - 4; j++) {
                if (strArr[j].equals(" ")) {
                    strArr[j] = strArr[j + 4];
                    strArr[j + 4] = " ";
                }
            }
        }
    }

    private void downMove(String[] strArr) {
        for (int i = strArr.length - 1; i > 3; i--) {
            for (int j = strArr.length - 1; j > 3; j--) {
                if (strArr[j].equals(" ")) {
                    strArr[j] = strArr[j - 4];
                    strArr[j - 4] = " ";
                }
            }
        }
    }

    private void leftMove(String[] strArr) {
        for (int m = 0; m < 4; m++) {

            for (int i = 1; i < 4; i++) {
                for (int j = i; j < strArr.length; j = j + 4) {
                    if (strArr[j - 1].equals(" ")) {
                        strArr[j - 1] = strArr[j];
                        strArr[j] = " ";
                    }
                }
            }
        }
    }

    private void rightMove(String[] strArr) {
        for (int m = 0; m < 4; m++) {

            for (int i = 3; i > 0; i--) {
                for (int j = i; j < strArr.length; j = j + 4) {
                    if (strArr[j].equals(" ")) {
                        strArr[j] = strArr[j - 1];
                        strArr[j - 1] = " ";
                    }
                }
            }
        }
    }

    // test
    public static void main(String[] args) {
        Two048 two048 = new Two048();
        String[] strArr = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
        Scanner scanner = new Scanner(System.in);
        while (true) {
            two048.generateNumber(strArr);
            System.out.println(two048.generateForm(strArr));
            System.out.println("Please input your choice(other input can exit): ");
            String select = scanner.next();
            two048.play(select.toLowerCase(), strArr);
        }
    }
}
