public class Letter {
    private StringBuffer sb;
    protected char Litt;
    protected int alf;
    protected String binary;

    public Letter(char Litt) {
        this.Litt=Litt;

        if((int)Litt>96){
            this.alf=(int)this.Litt-96;
        }else{
            this.alf=(int)this.Litt-64;
        }

        if(Litt!=32) {
            this.binary = Integer.toBinaryString(this.alf);
            if (this.binary.length() != 7) {
                sb = new StringBuffer(this.binary);
                while (sb.length() != 7) {
                    sb.insert(0, "0");
                }
                this.binary = sb.toString();
            }
        }else
            this.binary = null;
    }
    public String toString(){ return ""+ Litt;}
    public char getLitt(){ return Litt; }
    public String getBinary(){
        return binary;
    }
    public int getAlf(){
        return alf;
    }
}

