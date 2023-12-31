import dto.InputDTO;
import exception.InputEndException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {

    static Scanner sc = new Scanner(System.in);

    public static InputDTO makeRequest() {
        Pattern pattern = Pattern.compile("^[\\p{L}\\p{N}]+(\\?[\\p{L}\\p{N}]+=.*(&[\\p{L}\\p{N}]+=.*)*)?$");

        while (true) {
            System.out.printf("어떤 기능을 요청하시겠습니까? 종료를 원하시면 end를 입력하십시오 : ");
            try {
                String str = sc.nextLine();

                if (str.equals("end") || str.equals("END")) {
                    throw new InputEndException("\n--------------------------\nEND 입력 : 프로그램을 종료합니다.\n--------------------------");
                }

                Matcher matcher = pattern.matcher(str);

                if (!matcher.matches()) {
                    throw new IllegalArgumentException("잘못된 형식입니다. 다시 입력하십시오.");
                }

                InputDTO pDTO = parseString(str);
                return pDTO;
            } catch (InputEndException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("예기치 않은 문제가 발생했습니다. 다시 시도해 주십시오.");
            }
        }
    }

    public static InputDTO parseString(String str) {
        String[] parts = str.split("\\?");
        String methodName = parts[0];
        Map<String, String> parameters = new HashMap<>();

        if (parts.length > 1) {
            parameters = extractParameters(parts[1]);
        }

        return new InputDTO(methodName, parameters);
    }

    public static Map<String, String> extractParameters(String parameterLine) {
        Map<String, String> parameters = new HashMap<>();
        String[] paramArr = parameterLine.split("&");
        for (String param : paramArr) {
            String[] keyAndValue = param.split("=");
            if (keyAndValue.length == 2) {
                parameters.put(keyAndValue[0], keyAndValue[1]);
            }
        }
        return parameters;
    }

}
