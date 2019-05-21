package jun.prospring5.ch5;

public aspect MessageWrapper {

    private String prefix = "The Prefix";
    private String suffix = "The Suffix";

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    pointcut doWriting() :
            execution(*
            jun.prospring5.ch5.MessageWriter.writeMessage());

    before() : doWriting() {
        System.out.println(getPrefix());
    }

    after() : doWriting() {
        System.out.println(getSuffix());
    }
}
