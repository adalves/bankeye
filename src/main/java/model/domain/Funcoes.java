package model.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana Carolina Alves
 */
public class Funcoes {
    
    public void realizarSaque(Conta conta, double valor) {
        double saldo = conta.getSaldo();
        if ((saldo - valor) > 0) {
            conta.setSaldo(saldo - valor);
        }
        //update conta
    }
    
    public void realizarDeposito(Conta conta, double valor) {
        conta.setSaldo(conta.getSaldo() + valor);
        //update conta
    }
    
    public Poupanca criarPoupanca(int id, double valor, Calendar data) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Poupanca p = new Poupanca(id, valor, df.format(data.getTime()));
        //insert p
        PoupancaHistorico recent = new PoupancaHistorico(id, valor, df.format(data.getTime()));
        //insert recent
        rendimentoPoupanca(p, recent);
        return p;
    }
    
    public void realizarSaque(Poupanca poupanca, double valor) {
        double saldo = poupanca.getValor();
        if ((saldo - valor) > 0) {
            poupanca.setValor(saldo - valor);
        }
        //update poupanca
    }
    
    public void realizarDeposito(Poupanca poupanca, double valor) {
        poupanca.setValor(poupanca.getValor() + valor);
        //update poupanca
    }
        
    //atualiza o rendimendo da poupanca a partir da ultima atualizacao
    public void rendimentoPoupanca(Poupanca poupanca, PoupancaHistorico histRecente) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date date = df.parse(histRecente.getMes());
            Date today = new Date();
            Calendar recent = Calendar.getInstance();
            recent.setTime(date);
            recent.add(Calendar.MONTH, 1);
            double saldo = poupanca.getValor();
            while (today.after(recent.getTime())) {
                if (getSelic(recent) < 8.5) {
                    saldo += saldo * (((getSelic(recent) / 100) / 12) + getTaxaReferencial(recent));
                } else {
                    saldo += saldo * ((0.5 / 100) + getTaxaReferencial(recent));
                }
                poupanca.setValor(saldo);
                PoupancaHistorico ph = new PoupancaHistorico (poupanca.getId(), saldo, df.format(recent.getTime()));
                System.out.println(ph.getValorTotal() + " " + ph.getMes());
                //insert ph
                recent.add(Calendar.MONTH, 1);
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(Funcoes.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    //retorna uma estimativa de quando a poupanca rendera em 12 meses usando a selic de hoje
    public double estimativaPoupanca(Poupanca poupanca) {
        double saldo = poupanca.getValor();
        Date date = new Date();
        Calendar today = Calendar.getInstance();
        today.setTime(date);
        double selic = getSelic(today);
        double tr = getTaxaReferencial(today);
        
        for (int i = 0; i < 12; ++i) {
            if (selic < 8.5) {
                saldo += saldo * (((selic / 100) / 12) + tr);
            } else {
                saldo += saldo * ((0.5 / 100) +tr);
            }
        }
        return saldo;
    }
    
    private double getTaxaReferencial(Calendar data) {
        //get taxa da epoca
        return 0;
    }
    
    private double getSelic(Calendar data) {
        //get selic da epoca
        return 5.66;
    }
    
    //cria um emprestimo e parcelas para cada mes, deposita dinheiro na conta
    public Emprestimo realizarEmprestimo (Conta conta, int id, double valor, double juros, String data, String tipo, int qtdParcelas) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date = df.parse(data);
        Calendar dataParcl = Calendar.getInstance();
        dataParcl.setTime(date);
        double vlrParcela = valor/qtdParcelas;
        int[] idParcls = new int[qtdParcelas];
        for (int i = 0; i < qtdParcelas; ++i) {
            dataParcl.add(Calendar.MONTH, 1);
            Parcela p = new Parcela(id + i, vlrParcela, juros, vlrParcela + (vlrParcela * juros), df.format(dataParcl.getTime()), false);
            System.out.println(p.getId());
            idParcls[i] = p.getId();
            //insert parcela
        }
        Emprestimo e = new Emprestimo(id, valor, data, tipo, idParcls);
        //insert e
        realizarDeposito(conta, valor);
        return e;
    }
    
    //debita todas as parcelas vencidas da conta se o emprestimo for consignado
    public void pagarParcelas (Conta conta, Emprestimo emprestimo, ArrayList<Parcela> parcelas) throws ParseException {
        if (!emprestimo.getTipo().equals("Consignado")) return;
        Date today = new Date();
        int[] idParcelas = emprestimo.getParcelas();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < idParcelas.length; ++i) {
            Date venc = df.parse(parcelas.get(i).getVencimento());
            if (!parcelas.get(i).isPago() && venc.before(today)) {
                conta.setSaldo(conta.getSaldo() - parcelas.get(i).getTotal());
                parcelas.get(i).setPago(true);
                //update parcela
            }
        }
        //update emprestimo
        //update conta
    }
    
    //paga uma parcela em especifico
    public void pagarParcela (Conta conta, Parcela parcela) {
        conta.setSaldo(conta.getSaldo() - parcela.getTotal());
        parcela.setPago(true);
        //update parcela
        //update parcela
    }
}
