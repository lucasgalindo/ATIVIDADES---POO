import java.util.ArrayList;

class Conta {
    private String numero;
    private double saldo;
    private String nomeCliente;

    public Conta(String numero, double saldo, String nomeCliente) {
        this.numero = numero;
        this.saldo = saldo;
        this.nomeCliente = nomeCliente;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado na conta " + numero);
        } else {
            System.out.println("Saldo insuficiente para sacar R$" + valor + " na conta " + numero);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Conta outraConta = (Conta) obj;
        return numero.equals(outraConta.getNumero());
    }
}

class ContaNaoEncontradaException extends Exception {
    public ContaNaoEncontradaException(String message) {
        super(message);
    }
}

class Banco {
    private ArrayList<Conta> contas;

    public Banco() {
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public void removerConta(Conta conta) {
        contas.remove(conta);
    }

    public void depositar(String numeroConta, double valor) throws ContaNaoEncontradaException {
        Conta conta = buscarContaPorNumero(numeroConta);
        conta.depositar(valor);
        System.out.println("Depósito de R$" + valor + " realizado na conta " + numeroConta);
    }

    public void sacar(String numeroConta, double valor) throws ContaNaoEncontradaException {
        Conta conta = buscarContaPorNumero(numeroConta);
        conta.sacar(valor);
    }

    public Conta buscarContaPorNumero(String numeroConta) throws ContaNaoEncontradaException {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numeroConta)) {
                return conta;
            }
        }
        throw new ContaNaoEncontradaException("Conta não encontrada.");
    }

    public Conta buscarConta(Conta c) throws ContaNaoEncontradaException {
        for (Conta conta : contas) {
            if (conta.equals(c)) {
                return conta;
            }
        }
        throw new ContaNaoEncontradaException("Conta não encontrada.");
    }
}


public class main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        // Adicionando 3 contas
        Conta conta1 = new Conta("101", 1000.0, "João");
        Conta conta2 = new Conta("102", 2000.0, "Maria");
        Conta conta3 = new Conta("103", 3000.0, "Pedro");

        banco.adicionarConta(conta1);
        banco.adicionarConta(conta2);
        banco.adicionarConta(conta3);

        // Realizando operações
        try {
            banco.depositar("101", 500.0);
            banco.sacar("102", 4000.0);
        } catch (ContaNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }
}
