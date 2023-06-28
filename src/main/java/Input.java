import dto.InputDTO;
import exception.InputEndException;

import java.util.Arrays;
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

                if (str.equals("end")) {
                    throw new InputEndException("end 입력 : 프로그램을 종료합니다.");
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
            }
        }
    }

    public static InputDTO parseString(String str) {
        String[] parts = str.split("\\?");
        String methodName = parts[0];
        Map<String, String> parameters = new HashMap<>();

        // 파라미터가 존재한다면,
        if (parts.length > 1) {
            parameters = extractParameters(parts[1]);
        }

        System.out.println("확인용 / 메서드 이름 : " + methodName);
        System.out.println("확인용 / 파라미터 : " + parameters);

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
