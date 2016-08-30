package culik.br.com.listacompra.utils.model;

import java.io.Serializable;

/**
 * Created by LUIZ on 11/07/2016.
 */
public class Config  implements Serializable {
    private static final long serialVersionUID = -1;
    private boolean useFaceBook;
    private boolean sendEmail;
    private boolean sendWhats;
    private boolean sendSms;
    private boolean sendProdutoAuto;
    private boolean sendMercadoAutop;

    public boolean isUseGPSLocalAtual() {
        return useGPSLocalAtual;
    }

    public void setUseGPSLocalAtual(boolean useGPSLocalAtual) {
        this.useGPSLocalAtual = useGPSLocalAtual;
    }

    private boolean useGPSLocalAtual;


    private String  cabecSms;
    private String  rodapeSMS;
    private String  cabecEmail;
    private String  rodapeEmail;


    public Config(boolean useFaceBook, boolean sendEmail, boolean sendWhats, boolean sendSms, String cabecSms, String rodapeSMS, String cabecEmail, String rodapeEmail,boolean sendProdutoAuto,
            boolean sendMercadoAutop,boolean useGPSLocalAtual) {
        this.useFaceBook = useFaceBook;
        this.sendEmail = sendEmail;
        this.sendWhats = sendWhats;
        this.sendSms = sendSms;
        this.cabecSms = cabecSms;
        this.rodapeSMS = rodapeSMS;
        this.cabecEmail = cabecEmail;
        this.rodapeEmail = rodapeEmail;
        this.sendProdutoAuto = sendProdutoAuto;
        this.sendMercadoAutop = sendMercadoAutop;
        this.useGPSLocalAtual = useGPSLocalAtual;
    }

    public String getRodapeSMS() {

        return rodapeSMS;
    }

    public void setRodapeSMS(String rodapeSMS) {
        this.rodapeSMS = rodapeSMS;
    }

    public String getCabecEmail() {
        return cabecEmail;
    }

    public void setCabecEmail(String cabecEmail) {
        this.cabecEmail = cabecEmail;
    }

    public String getRodapeEmail() {
        return rodapeEmail;
    }

    public void setRodapeEmail(String rodapeEmail) {
        this.rodapeEmail = rodapeEmail;
    }

    public String getCabecSms() {

        return cabecSms;
    }

    public void setCabecSms(String cabecSms) {
        this.cabecSms = cabecSms;
    }



    public boolean isUseFaceBook() {

        return useFaceBook;
    }

    public void setUseFaceBook(boolean useFaceBook) {
        this.useFaceBook = useFaceBook;
    }

    public boolean isSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public boolean isSendWhats() {
        return sendWhats;
    }

    public void setSendWhats(boolean sendWhats) {
        this.sendWhats = sendWhats;
    }

    public boolean isSendSms() {
        return sendSms;
    }

    public void setSendSms(boolean sendSms) {
        this.sendSms = sendSms;
    }

    public boolean isSendProdutoAuto() {
        return sendProdutoAuto;
    }

    public void setSendProdutoAuto(boolean sendProdutoAuto) {
        this.sendProdutoAuto = sendProdutoAuto;
    }

    public boolean isSendMercadoAutop() {
        return sendMercadoAutop;
    }

    public void setSendMercadoAutop(boolean sendMercadoAutop) {
        this.sendMercadoAutop = sendMercadoAutop;
    }

}
