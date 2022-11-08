package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqresinNamePojo {

    private String name;

    public ReqresinNamePojo(String name) {
        this.name = name;
    }

    public ReqresinNamePojo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ReqresinNamePojo{" +
                "name='" + name + '\'' +
                '}';
    }
}
