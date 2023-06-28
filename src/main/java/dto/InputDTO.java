package dto;

import java.util.Map;

public class InputDTO {

    String methodName;
    Map<String, String> parameters;

    public InputDTO() {
    }

    public InputDTO(String methodName, Map<String, String> parameters) {
        this.methodName = methodName;
        this.parameters = parameters;
    }

    public String getMethodName() {
        return methodName;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
