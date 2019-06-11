package jun.prospring5.ch9.transaction;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

public class AtomikosJtaPlatform extends AbstractJtaPlatform {

    private static UserTransaction userTx;
    private static TransactionManager txMgr;

    @Override
    protected TransactionManager locateTransactionManager() {
        return txMgr;
    }

    @Override
    protected UserTransaction locateUserTransaction() {
        return userTx;
    }

    public static UserTransaction getUserTx() {
        return userTx;
    }

    public static TransactionManager getTxMgr() {
        return txMgr;
    }

    public static void setUserTx(UserTransaction userTx) {
        AtomikosJtaPlatform.userTx = userTx;
    }

    public static void setTxMgr(TransactionManager txMgr) {
        AtomikosJtaPlatform.txMgr = txMgr;
    }
}
