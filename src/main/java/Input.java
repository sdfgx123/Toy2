import dto.InputDTO;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Input {

    static Scanner sc = new Scanner(System.in);

    public static InputDTO makeRequest() {
        while (true) {
            System.out.println("어떤 기능을 요청하시겠습니까?");
            String str = sc.next();
            InputDTO pDTO = parseString(str);
            return pDTO;
            // 입력 예외처리 추가 필요
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
