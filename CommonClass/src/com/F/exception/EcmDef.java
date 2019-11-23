package com.F.exception;

public class EcmDef {
    public static void main(String[] args) {
        EcmDef ed = new EcmDef();

        try{
            int dividen = Integer.parseInt(args[0]);
            int divide = Integer.parseInt(args[1]);
            ed.ecm(dividen,divide);
        }catch (NumberFormatException e){
            System.out.println(e);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }catch (ArithmeticException e){
            System.out.println(e);
        }catch (EcDef e){
            System.out.println(e);
        }

    }

    public double ecm(int dividen,int divide) {
        if(divide < 0 || dividen < 0) {
            throw new EcDef("不能输入负数");
        }
        return divide/dividen;
    }

    class EcDef extends RuntimeException{
        public EcDef(){
        }
        public EcDef(String msg){
            super(msg);
        }
    }
}
