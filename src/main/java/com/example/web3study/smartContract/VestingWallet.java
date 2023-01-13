package com.example.web3study.smartContract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
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
public class VestingWallet extends Contract {
    public static final String BINARY = "0x60e0604052604051610cd8380380610cd8833981016040819052610022916100d0565b6001600160a01b03831661008f5760405162461bcd60e51b815260206004820152602a60248201527f56657374696e6757616c6c65743a2062656e6566696369617279206973207a65604482015269726f206164647265737360b01b606482015260840160405180910390fd5b6001600160a01b039092166080526001600160401b0390811660a0521660c052610121565b80516001600160401b03811681146100cb57600080fd5b919050565b6000806000606084860312156100e557600080fd5b83516001600160a01b03811681146100fc57600080fd5b925061010a602085016100b4565b9150610118604085016100b4565b90509250925092565b60805160a05160c051610b6d61016b6000396000818160ee015281816104ce01526105230152600061046a015260008181610158015281816102f1015261040a0152610b6d6000f3fe6080604052600436106100a05760003560e01c806386d1a69f1161006457806386d1a69f146101a257806396132521146101b75780639852595c146101cc578063a3f8eace14610202578063be9a655514610222578063fbccedae1461023757600080fd5b80630a17b06b146100ac5780630fb5a6b4146100df578063191655871461011c57806338af3eed1461013e578063810ec23b1461018257600080fd5b366100a757005b600080fd5b3480156100b857600080fd5b506100cc6100c7366004610977565b61024c565b6040519081526020015b60405180910390f35b3480156100eb57600080fd5b507f000000000000000000000000000000000000000000000000000000000000000067ffffffffffffffff166100cc565b34801561012857600080fd5b5061013c6101373660046109a9565b610270565b005b34801561014a57600080fd5b506040516001600160a01b037f00000000000000000000000000000000000000000000000000000000000000001681526020016100d6565b34801561018e57600080fd5b506100cc61019d3660046109c4565b61031a565b3480156101ae57600080fd5b5061013c6103b0565b3480156101c357600080fd5b506000546100cc565b3480156101d857600080fd5b506100cc6101e73660046109a9565b6001600160a01b031660009081526001602052604090205490565b34801561020e57600080fd5b506100cc61021d3660046109a9565b610432565b34801561022e57600080fd5b506100cc61045f565b34801561024357600080fd5b506100cc61048d565b600061026a61025a60005490565b6102649047610a0d565b836104a9565b92915050565b600061027b82610432565b6001600160a01b0383166000908152600160205260408120805492935083929091906102a8908490610a0d565b90915550506040518181526001600160a01b038316907fc0e523490dd523c33b1878c9eb14ff46991e3f5b2cd33710918618f2a39cba1b9060200160405180910390a2610316827f000000000000000000000000000000000000000000000000000000000000000083610583565b5050565b6001600160a01b0382166000908152600160205260408120546103a9906040516370a0823160e01b81523060048201526001600160a01b038616906370a0823190602401602060405180830381865afa15801561037b573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061039f9190610a25565b6102649190610a0d565b9392505050565b60006103ba61048d565b9050806000808282546103cd9190610a0d565b90915550506040518181527fda9d4e5f101b8b9b1c5b76d0c5a9f7923571acfc02376aa076b75a8c080c956b9060200160405180910390a161042f7f0000000000000000000000000000000000000000000000000000000000000000826105da565b50565b6001600160a01b038116600090815260016020526040812054610455834261031a565b61026a9190610a3e565b67ffffffffffffffff7f00000000000000000000000000000000000000000000000000000000000000001690565b6000805461049a4261024c565b6104a49190610a3e565b905090565b60006104b361045f565b8267ffffffffffffffff1610156104cc5750600061026a565b7f000000000000000000000000000000000000000000000000000000000000000067ffffffffffffffff166104ff61045f565b6105099190610a0d565b8267ffffffffffffffff16111561052157508161026a565b7f000000000000000000000000000000000000000000000000000000000000000067ffffffffffffffff1661055461045f565b6105689067ffffffffffffffff8516610a3e565b6105729085610a55565b61057c9190610a74565b905061026a565b604080516001600160a01b038416602482015260448082018490528251808303909101815260649091019091526020810180516001600160e01b031663a9059cbb60e01b1790526105d59084906106f8565b505050565b8047101561062f5760405162461bcd60e51b815260206004820152601d60248201527f416464726573733a20696e73756666696369656e742062616c616e636500000060448201526064015b60405180910390fd5b6000826001600160a01b03168260405160006040518083038185875af1925050503d806000811461067c576040519150601f19603f3d011682016040523d82523d6000602084013e610681565b606091505b50509050806105d55760405162461bcd60e51b815260206004820152603a60248201527f416464726573733a20756e61626c6520746f2073656e642076616c75652c207260448201527f6563697069656e74206d617920686176652072657665727465640000000000006064820152608401610626565b600061074d826040518060400160405280602081526020017f5361666545524332303a206c6f772d6c6576656c2063616c6c206661696c6564815250856001600160a01b03166107ca9092919063ffffffff16565b8051909150156105d5578080602001905181019061076b9190610a96565b6105d55760405162461bcd60e51b815260206004820152602a60248201527f5361666545524332303a204552433230206f7065726174696f6e20646964206e6044820152691bdd081cdd58d8d9595960b21b6064820152608401610626565b60606107d984846000856107e1565b949350505050565b6060824710156108425760405162461bcd60e51b815260206004820152602660248201527f416464726573733a20696e73756666696369656e742062616c616e636520666f6044820152651c8818d85b1b60d21b6064820152608401610626565b600080866001600160a01b0316858760405161085e9190610ae8565b60006040518083038185875af1925050503d806000811461089b576040519150601f19603f3d011682016040523d82523d6000602084013e6108a0565b606091505b50915091506108b1878383876108bc565b979650505050505050565b6060831561092b578251600003610924576001600160a01b0385163b6109245760405162461bcd60e51b815260206004820152601d60248201527f416464726573733a2063616c6c20746f206e6f6e2d636f6e74726163740000006044820152606401610626565b50816107d9565b6107d983838151156109405781518083602001fd5b8060405162461bcd60e51b81526004016106269190610b04565b803567ffffffffffffffff8116811461097257600080fd5b919050565b60006020828403121561098957600080fd5b6103a98261095a565b80356001600160a01b038116811461097257600080fd5b6000602082840312156109bb57600080fd5b6103a982610992565b600080604083850312156109d757600080fd5b6109e083610992565b91506109ee6020840161095a565b90509250929050565b634e487b7160e01b600052601160045260246000fd5b60008219821115610a2057610a206109f7565b500190565b600060208284031215610a3757600080fd5b5051919050565b600082821015610a5057610a506109f7565b500390565b6000816000190483118215151615610a6f57610a6f6109f7565b500290565b600082610a9157634e487b7160e01b600052601260045260246000fd5b500490565b600060208284031215610aa857600080fd5b815180151581146103a957600080fd5b60005b83811015610ad3578181015183820152602001610abb565b83811115610ae2576000848401525b50505050565b60008251610afa818460208701610ab8565b9190910192915050565b6020815260008251806020840152610b23816040850160208701610ab8565b601f01601f1916919091016040019291505056fea2646970667358221220fdd95847710132d35d71180361a9570888ae92d2db77076536135b74a7b0c25364736f6c634300080d0033";

    public static final String FUNC_BENEFICIARY = "beneficiary";

    public static final String FUNC_DURATION = "duration";

    public static final String FUNC_releasable = "releasable";

    public static final String FUNC_release = "release";

    public static final String FUNC_released = "released";

    public static final String FUNC_START = "start";

    public static final String FUNC_vestedAmount = "vestedAmount";

    public static final Event ERC20RELEASED_EVENT = new Event("ERC20Released", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ETHERRELEASED_EVENT = new Event("EtherReleased", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected VestingWallet(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected VestingWallet(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected VestingWallet(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected VestingWallet(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ERC20ReleasedEventResponse> getERC20ReleasedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ERC20RELEASED_EVENT, transactionReceipt);
        ArrayList<ERC20ReleasedEventResponse> responses = new ArrayList<ERC20ReleasedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ERC20ReleasedEventResponse typedResponse = new ERC20ReleasedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.token = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ERC20ReleasedEventResponse> eRC20ReleasedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ERC20ReleasedEventResponse>() {
            @Override
            public ERC20ReleasedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ERC20RELEASED_EVENT, log);
                ERC20ReleasedEventResponse typedResponse = new ERC20ReleasedEventResponse();
                typedResponse.log = log;
                typedResponse.token = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ERC20ReleasedEventResponse> eRC20ReleasedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ERC20RELEASED_EVENT));
        return eRC20ReleasedEventFlowable(filter);
    }

    public static List<EtherReleasedEventResponse> getEtherReleasedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ETHERRELEASED_EVENT, transactionReceipt);
        ArrayList<EtherReleasedEventResponse> responses = new ArrayList<EtherReleasedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EtherReleasedEventResponse typedResponse = new EtherReleasedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<EtherReleasedEventResponse> etherReleasedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, EtherReleasedEventResponse>() {
            @Override
            public EtherReleasedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ETHERRELEASED_EVENT, log);
                EtherReleasedEventResponse typedResponse = new EtherReleasedEventResponse();
                typedResponse.log = log;
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<EtherReleasedEventResponse> etherReleasedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ETHERRELEASED_EVENT));
        return etherReleasedEventFlowable(filter);
    }

    public RemoteFunctionCall<String> beneficiary() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BENEFICIARY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> duration() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DURATION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> releasable(String token) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_releasable, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> releasable() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_releasable, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> release(String token) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_release, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> release() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_release, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> released() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_released, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> released(String token) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_released, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> start() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_START, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> vestedAmount(BigInteger timestamp) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_vestedAmount, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint64(timestamp)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> vestedAmount(String token, BigInteger timestamp) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_vestedAmount, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, token), 
                new org.web3j.abi.datatypes.generated.Uint64(timestamp)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static VestingWallet load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new VestingWallet(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static VestingWallet load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new VestingWallet(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static VestingWallet load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new VestingWallet(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static VestingWallet load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new VestingWallet(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<VestingWallet> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger initialWeiValue, String beneficiaryAddress, BigInteger startTimestamp, BigInteger durationSeconds) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, beneficiaryAddress), 
                new org.web3j.abi.datatypes.generated.Uint64(startTimestamp), 
                new org.web3j.abi.datatypes.generated.Uint64(durationSeconds)));
        return deployRemoteCall(VestingWallet.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor, initialWeiValue);
    }

    public static RemoteCall<VestingWallet> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger initialWeiValue, String beneficiaryAddress, BigInteger startTimestamp, BigInteger durationSeconds) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, beneficiaryAddress), 
                new org.web3j.abi.datatypes.generated.Uint64(startTimestamp), 
                new org.web3j.abi.datatypes.generated.Uint64(durationSeconds)));
        return deployRemoteCall(VestingWallet.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor, initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<VestingWallet> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String beneficiaryAddress, BigInteger startTimestamp, BigInteger durationSeconds) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, beneficiaryAddress), 
                new org.web3j.abi.datatypes.generated.Uint64(startTimestamp), 
                new org.web3j.abi.datatypes.generated.Uint64(durationSeconds)));
        return deployRemoteCall(VestingWallet.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    @Deprecated
    public static RemoteCall<VestingWallet> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String beneficiaryAddress, BigInteger startTimestamp, BigInteger durationSeconds) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, beneficiaryAddress), 
                new org.web3j.abi.datatypes.generated.Uint64(startTimestamp), 
                new org.web3j.abi.datatypes.generated.Uint64(durationSeconds)));
        return deployRemoteCall(VestingWallet.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static class ERC20ReleasedEventResponse extends BaseEventResponse {
        public String token;

        public BigInteger amount;
    }

    public static class EtherReleasedEventResponse extends BaseEventResponse {
        public BigInteger amount;
    }
}
