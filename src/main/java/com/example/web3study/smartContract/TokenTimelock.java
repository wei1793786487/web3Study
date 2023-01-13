package com.example.web3study.smartContract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.9.4.
 */
@SuppressWarnings("rawtypes")
public class TokenTimelock extends Contract {
    public static final String BINARY = "0x60e060405234801561001057600080fd5b506040516107ca3803806107ca83398101604081905261002f916100d0565b42811161009d5760405162461bcd60e51b815260206004820152603260248201527f546f6b656e54696d656c6f636b3a2072656c656173652074696d65206973206260448201527165666f72652063757272656e742074696d6560701b606482015260840160405180910390fd5b6001600160a01b03928316608052911660a05260c052610113565b6001600160a01b03811681146100cd57600080fd5b50565b6000806000606084860312156100e557600080fd5b83516100f0816100b8565b6020850151909350610101816100b8565b80925050604084015190509250925092565b60805160a05160c05161067061015a60003960008181609f015260f00152600081816053015261029801526000818160ca01528181610182015261027601526106706000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c806338af3eed1461005157806386d1a69f14610090578063b91d40011461009a578063fc0c546a146100c8575b600080fd5b7f00000000000000000000000000000000000000000000000000000000000000005b6040516001600160a01b0390911681526020015b60405180910390f35b6100986100ee565b005b6040517f00000000000000000000000000000000000000000000000000000000000000008152602001610087565b7f0000000000000000000000000000000000000000000000000000000000000000610073565b7f000000000000000000000000000000000000000000000000000000000000000042101561017e5760405162461bcd60e51b815260206004820152603260248201527f546f6b656e54696d656c6f636b3a2063757272656e742074696d65206973206260448201527165666f72652072656c656173652074696d6560701b60648201526084015b60405180910390fd5b60007f00000000000000000000000000000000000000000000000000000000000000006040516370a0823160e01b81523060048201526001600160a01b0391909116906370a0823190602401602060405180830381865afa1580156101e7573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061020b9190610579565b9050600081116102695760405162461bcd60e51b815260206004820152602360248201527f546f6b656e54696d656c6f636b3a206e6f20746f6b656e7320746f2072656c6560448201526261736560e81b6064820152608401610175565b6102bd6001600160a01b037f0000000000000000000000000000000000000000000000000000000000000000167f0000000000000000000000000000000000000000000000000000000000000000836102c0565b50565b604080516001600160a01b038416602482015260448082018490528251808303909101815260649091019091526020810180516001600160e01b031663a9059cbb60e01b179052610312908490610317565b505050565b600061036c826040518060400160405280602081526020017f5361666545524332303a206c6f772d6c6576656c2063616c6c206661696c6564815250856001600160a01b03166103e99092919063ffffffff16565b805190915015610312578080602001905181019061038a9190610592565b6103125760405162461bcd60e51b815260206004820152602a60248201527f5361666545524332303a204552433230206f7065726174696f6e20646964206e6044820152691bdd081cdd58d8d9595960b21b6064820152608401610175565b60606103f88484600085610400565b949350505050565b6060824710156104615760405162461bcd60e51b815260206004820152602660248201527f416464726573733a20696e73756666696369656e742062616c616e636520666f6044820152651c8818d85b1b60d21b6064820152608401610175565b600080866001600160a01b0316858760405161047d91906105eb565b60006040518083038185875af1925050503d80600081146104ba576040519150601f19603f3d011682016040523d82523d6000602084013e6104bf565b606091505b50915091506104d0878383876104db565b979650505050505050565b6060831561054a578251600003610543576001600160a01b0385163b6105435760405162461bcd60e51b815260206004820152601d60248201527f416464726573733a2063616c6c20746f206e6f6e2d636f6e74726163740000006044820152606401610175565b50816103f8565b6103f8838381511561055f5781518083602001fd5b8060405162461bcd60e51b81526004016101759190610607565b60006020828403121561058b57600080fd5b5051919050565b6000602082840312156105a457600080fd5b815180151581146105b457600080fd5b9392505050565b60005b838110156105d65781810151838201526020016105be565b838111156105e5576000848401525b50505050565b600082516105fd8184602087016105bb565b9190910192915050565b60208152600082518060208401526106268160408501602087016105bb565b601f01601f1916919091016040019291505056fea2646970667358221220a3c1986b0bad64201799aa861613991e166f44314a52b7aa229d6806aa9714b364736f6c634300080d0033";

    public static final String FUNC_BENEFICIARY = "beneficiary";

    public static final String FUNC_RELEASE = "release";

    public static final String FUNC_RELEASETIME = "releaseTime";

    public static final String FUNC_TOKEN = "token";

    @Deprecated
    protected TokenTimelock(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TokenTimelock(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TokenTimelock(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TokenTimelock(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> beneficiary() {
        final Function function = new Function(FUNC_BENEFICIARY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> release() {
        final Function function = new Function(
                FUNC_RELEASE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> releaseTime() {
        final Function function = new Function(FUNC_RELEASETIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> token() {
        final Function function = new Function(FUNC_TOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static TokenTimelock load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenTimelock(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TokenTimelock load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenTimelock(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TokenTimelock load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TokenTimelock(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TokenTimelock load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TokenTimelock(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TokenTimelock> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String token_, String beneficiary_, BigInteger releaseTime_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token_), 
                new org.web3j.abi.datatypes.Address(160, beneficiary_), 
                new org.web3j.abi.datatypes.generated.Uint256(releaseTime_)));
        return deployRemoteCall(TokenTimelock.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<TokenTimelock> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String token_, String beneficiary_, BigInteger releaseTime_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token_), 
                new org.web3j.abi.datatypes.Address(160, beneficiary_), 
                new org.web3j.abi.datatypes.generated.Uint256(releaseTime_)));
        return deployRemoteCall(TokenTimelock.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TokenTimelock> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String token_, String beneficiary_, BigInteger releaseTime_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token_), 
                new org.web3j.abi.datatypes.Address(160, beneficiary_), 
                new org.web3j.abi.datatypes.generated.Uint256(releaseTime_)));
        return deployRemoteCall(TokenTimelock.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TokenTimelock> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String token_, String beneficiary_, BigInteger releaseTime_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token_), 
                new org.web3j.abi.datatypes.Address(160, beneficiary_), 
                new org.web3j.abi.datatypes.generated.Uint256(releaseTime_)));
        return deployRemoteCall(TokenTimelock.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }
}
