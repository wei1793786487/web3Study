package com.example.web3study.smartContract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
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
public class ERC777 extends Contract {
    public static final String BINARY = "0x60806040523480156200001157600080fd5b5060405162001bab38038062001bab8339810160408190526200003491620003f1565b82516200004990600290602086019062000215565b5081516200005f90600390602085019062000215565b50805162000075906004906020840190620002a4565b5060005b8151811015620000e5576001600560008484815181106200009e576200009e62000508565b6020908102919091018101516001600160a01b03168252810191909152604001600020805460ff191691151591909117905580620000dc816200051e565b91505062000079565b506040516329965a1d60e01b815230600482018190527fac7fbab5f54a3ca8194167523c6753bfeb96a445279294b6125b68cce217705460248301526044820152731820a4b7618bde71dce8cdc73aab6c95905fad24906329965a1d90606401600060405180830381600087803b1580156200016057600080fd5b505af115801562000175573d6000803e3d6000fd5b50506040516329965a1d60e01b815230600482018190527faea199e31a596269b42cdafd93407f14436db6e4cad65417994c2eb37381e05a60248301526044820152731820a4b7618bde71dce8cdc73aab6c95905fad2492506329965a1d9150606401600060405180830381600087803b158015620001f357600080fd5b505af115801562000208573d6000803e3d6000fd5b5050505050505062000582565b828054620002239062000546565b90600052602060002090601f01602090048101928262000247576000855562000292565b82601f106200026257805160ff191683800117855562000292565b8280016001018555821562000292579182015b828111156200029257825182559160200191906001019062000275565b50620002a0929150620002fc565b5090565b82805482825590600052602060002090810192821562000292579160200282015b828111156200029257825182546001600160a01b0319166001600160a01b03909116178255602090920191600190910190620002c5565b5b80821115620002a05760008155600101620002fd565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f191681016001600160401b038111828210171562000354576200035462000313565b604052919050565b600082601f8301126200036e57600080fd5b81516001600160401b038111156200038a576200038a62000313565b6020620003a0601f8301601f1916820162000329565b8281528582848701011115620003b557600080fd5b60005b83811015620003d5578581018301518282018401528201620003b8565b83811115620003e75760008385840101525b5095945050505050565b6000806000606084860312156200040757600080fd5b83516001600160401b03808211156200041f57600080fd5b6200042d878388016200035c565b94506020915081860151818111156200044557600080fd5b62000453888289016200035c565b9450506040860151818111156200046957600080fd5b8601601f810188136200047b57600080fd5b80518281111562000490576200049062000313565b8060051b9250620004a384840162000329565b818152928201840192848101908a851115620004be57600080fd5b928501925b84841015620004f857835192506001600160a01b0383168314620004e75760008081fd5b8282529285019290850190620004c3565b8096505050505050509250925092565b634e487b7160e01b600052603260045260246000fd5b6000600182016200053f57634e487b7160e01b600052601160045260246000fd5b5060010190565b600181811c908216806200055b57607f821691505b6020821081036200057c57634e487b7160e01b600052602260045260246000fd5b50919050565b61161980620005926000396000f3fe608060405234801561001057600080fd5b50600436106101165760003560e01c8063959b8c3f116100a2578063d95b637111610071578063d95b63711461022b578063dd62ed3e1461023e578063fad8b32a14610277578063fc673c4f1461028a578063fe9d93031461029d57600080fd5b8063959b8c3f146101ea57806395d89b41146101fd5780639bd9bbc614610205578063a9059cbb1461021857600080fd5b806323b872dd116100e957806323b872dd14610183578063313ce56714610196578063556f0dc7146101a557806362ad1b83146101ac57806370a08231146101c157600080fd5b806306e485381461011b57806306fdde0314610139578063095ea7b31461014e57806318160ddd14610171575b600080fd5b6101236102b0565b604051610130919061108e565b60405180910390f35b610141610312565b6040516101309190611128565b61016161015c366004611153565b61039b565b6040519015158152602001610130565b6001545b604051908152602001610130565b61016161019136600461117f565b6103b3565b60405160128152602001610130565b6001610175565b6101bf6101ba366004611263565b6103f9565b005b6101756101cf3660046112f6565b6001600160a01b031660009081526020819052604090205490565b6101bf6101f83660046112f6565b61043e565b61014161055b565b6101bf610213366004611313565b61056a565b610161610226366004611153565b61058d565b61016161023936600461136c565b6105c5565b61017561024c36600461136c565b6001600160a01b03918216600090815260086020908152604080832093909416825291909152205490565b6101bf6102853660046112f6565b610667565b6101bf6102983660046113a5565b610782565b6101bf6102ab366004611425565b6107ba565b6060600480548060200260200160405190810160405280929190818152602001828054801561030857602002820191906000526020600020905b81546001600160a01b031681526001909101906020018083116102ea575b5050505050905090565b6060600280546103219061146c565b80601f016020809104026020016040519081016040528092919081815260200182805461034d9061146c565b80156103085780601f1061036f57610100808354040283529160200191610308565b820191906000526020600020905b81548152906001019060200180831161037d57509395945050505050565b6000336103a98185856107d9565b5060019392505050565b6000336103c1858285610900565b6103ee8585856040518060200160405280600081525060405180602001604052806000815250600061098c565b506001949350505050565b61040333866105c5565b6104285760405162461bcd60e51b815260040161041f906114a6565b60405180910390fd5b6104378585858585600161098c565b5050505050565b6001600160a01b03811633036104a25760405162461bcd60e51b8152602060048201526024808201527f4552433737373a20617574686f72697a696e672073656c66206173206f70657260448201526330ba37b960e11b606482015260840161041f565b6001600160a01b03811660009081526005602052604090205460ff16156104f3573360009081526007602090815260408083206001600160a01b03851684529091529020805460ff19169055610522565b3360009081526006602090815260408083206001600160a01b03851684529091529020805460ff191660011790555b60405133906001600160a01b038316907ff4caeb2d6ca8932a215a353d0703c326ec2d81fc68170f320eb2ab49e9df61f990600090a350565b6060600380546103219061146c565b6105883384848460405180602001604052806000815250600161098c565b505050565b60006105bc3384846040518060200160405280600081525060405180602001604052806000815250600061098c565b50600192915050565b6000816001600160a01b0316836001600160a01b0316148061063057506001600160a01b03831660009081526005602052604090205460ff16801561063057506001600160a01b0380831660009081526007602090815260408083209387168352929052205460ff16155b8061066057506001600160a01b0380831660009081526006602090815260408083209387168352929052205460ff165b9392505050565b336001600160a01b038216036106c95760405162461bcd60e51b815260206004820152602160248201527f4552433737373a207265766f6b696e672073656c66206173206f70657261746f6044820152603960f91b606482015260840161041f565b6001600160a01b03811660009081526005602052604090205460ff161561071d573360009081526007602090815260408083206001600160a01b03851684529091529020805460ff19166001179055610749565b3360009081526006602090815260408083206001600160a01b03851684529091529020805460ff191690555b60405133906001600160a01b038316907f50546e66e5f44d728365dc3908c63bc5cfeeab470722c1677e3073a6ac294aa190600090a350565b61078c33856105c5565b6107a85760405162461bcd60e51b815260040161041f906114a6565b6107b484848484610a88565b50505050565b6107d533838360405180602001604052806000815250610a88565b5050565b6001600160a01b03831661083d5760405162461bcd60e51b815260206004820152602560248201527f4552433737373a20617070726f76652066726f6d20746865207a65726f206164604482015264647265737360d81b606482015260840161041f565b6001600160a01b03821661089f5760405162461bcd60e51b815260206004820152602360248201527f4552433737373a20617070726f766520746f20746865207a65726f206164647260448201526265737360e81b606482015260840161041f565b6001600160a01b0383811660008181526008602090815260408083209487168084529482529182902085905590518481527f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925910160405180910390a3505050565b6001600160a01b0383811660009081526008602090815260408083209386168352929052205460001981146107b4578181101561097f5760405162461bcd60e51b815260206004820152601e60248201527f4552433737373a20696e73756666696369656e7420616c6c6f77616e63650000604482015260640161041f565b6107b484848484036107d9565b6001600160a01b0386166109f15760405162461bcd60e51b815260206004820152602660248201527f4552433737373a207472616e736665722066726f6d20746865207a65726f206160448201526564647265737360d01b606482015260840161041f565b6001600160a01b038516610a535760405162461bcd60e51b8152602060048201526024808201527f4552433737373a207472616e7366657220746f20746865207a65726f206164646044820152637265737360e01b606482015260840161041f565b33610a62818888888888610c3c565b610a70818888888888610d63565b610a7f81888888888888610ec9565b50505050505050565b6001600160a01b038416610ae95760405162461bcd60e51b815260206004820152602260248201527f4552433737373a206275726e2066726f6d20746865207a65726f206164647265604482015261737360f01b606482015260840161041f565b33610af981866000878787610c3c565b6001600160a01b03851660009081526020819052604090205484811015610b6e5760405162461bcd60e51b815260206004820152602360248201527f4552433737373a206275726e20616d6f756e7420657863656564732062616c616044820152626e636560e81b606482015260840161041f565b6001600160a01b0386166000908152602081905260408120868303905560018054879290610b9d908490611508565b92505081905550856001600160a01b0316826001600160a01b03167fa78a9be3a7b862d26933ad85fb11d80ef66b8f972d7cbba06621d583943a4098878787604051610beb9392919061151f565b60405180910390a36040518581526000906001600160a01b038816907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9060200160405180910390a3505050505050565b60405163555ddc6560e11b81526001600160a01b03861660048201527f29ddb589b1fb5fc7cf394961c1adf5f8c6454761adf795e67fe149f658abe8956024820152600090731820a4b7618bde71dce8cdc73aab6c95905fad249063aabbb8ca90604401602060405180830381865afa158015610cbd573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610ce19190611554565b90506001600160a01b03811615610a7f57604051633ad5cbc160e11b81526001600160a01b038216906375ab978290610d28908a908a908a908a908a908a90600401611571565b600060405180830381600087803b158015610d4257600080fd5b505af1158015610d56573d6000803e3d6000fd5b5050505050505050505050565b6001600160a01b03851660009081526020819052604090205483811015610ddc5760405162461bcd60e51b815260206004820152602760248201527f4552433737373a207472616e7366657220616d6f756e7420657863656564732060448201526662616c616e636560c81b606482015260840161041f565b6001600160a01b03808716600090815260208190526040808220878503905591871681529081208054869290610e139084906115cb565b92505081905550846001600160a01b0316866001600160a01b0316886001600160a01b03167f06b541ddaa720db2b10a4d0cdac39b8d360425fc073085fac19bc82614677987878787604051610e6b9392919061151f565b60405180910390a4846001600160a01b0316866001600160a01b03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef86604051610eb891815260200190565b60405180910390a350505050505050565b60405163555ddc6560e11b81526001600160a01b03861660048201527fb281fc8c12954d22544db45de3159a39272895b169a852b314f9cc762e44c53b6024820152600090731820a4b7618bde71dce8cdc73aab6c95905fad249063aabbb8ca90604401602060405180830381865afa158015610f4a573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610f6e9190611554565b90506001600160a01b03811615610fea576040516223de2960e01b81526001600160a01b038216906223de2990610fb3908b908b908b908b908b908b90600401611571565b600060405180830381600087803b158015610fcd57600080fd5b505af1158015610fe1573d6000803e3d6000fd5b50505050611084565b8115611084576001600160a01b0386163b156110845760405162461bcd60e51b815260206004820152604d60248201527f4552433737373a20746f6b656e20726563697069656e7420636f6e747261637460448201527f20686173206e6f20696d706c656d656e74657220666f7220455243373737546f60648201526c1ad95b9cd49958da5c1a595b9d609a1b608482015260a40161041f565b5050505050505050565b6020808252825182820181905260009190848201906040850190845b818110156110cf5783516001600160a01b0316835292840192918401916001016110aa565b50909695505050505050565b6000815180845260005b81811015611101576020818501810151868301820152016110e5565b81811115611113576000602083870101525b50601f01601f19169290920160200192915050565b60208152600061066060208301846110db565b6001600160a01b038116811461115057600080fd5b50565b6000806040838503121561116657600080fd5b82356111718161113b565b946020939093013593505050565b60008060006060848603121561119457600080fd5b833561119f8161113b565b925060208401356111af8161113b565b929592945050506040919091013590565b634e487b7160e01b600052604160045260246000fd5b600082601f8301126111e757600080fd5b813567ffffffffffffffff80821115611202576112026111c0565b604051601f8301601f19908116603f0116810190828211818310171561122a5761122a6111c0565b8160405283815286602085880101111561124357600080fd5b836020870160208301376000602085830101528094505050505092915050565b600080600080600060a0868803121561127b57600080fd5b85356112868161113b565b945060208601356112968161113b565b935060408601359250606086013567ffffffffffffffff808211156112ba57600080fd5b6112c689838a016111d6565b935060808801359150808211156112dc57600080fd5b506112e9888289016111d6565b9150509295509295909350565b60006020828403121561130857600080fd5b81356106608161113b565b60008060006060848603121561132857600080fd5b83356113338161113b565b925060208401359150604084013567ffffffffffffffff81111561135657600080fd5b611362868287016111d6565b9150509250925092565b6000806040838503121561137f57600080fd5b823561138a8161113b565b9150602083013561139a8161113b565b809150509250929050565b600080600080608085870312156113bb57600080fd5b84356113c68161113b565b935060208501359250604085013567ffffffffffffffff808211156113ea57600080fd5b6113f6888389016111d6565b9350606087013591508082111561140c57600080fd5b50611419878288016111d6565b91505092959194509250565b6000806040838503121561143857600080fd5b82359150602083013567ffffffffffffffff81111561145657600080fd5b611462858286016111d6565b9150509250929050565b600181811c9082168061148057607f821691505b6020821081036114a057634e487b7160e01b600052602260045260246000fd5b50919050565b6020808252602c908201527f4552433737373a2063616c6c6572206973206e6f7420616e206f70657261746f60408201526b39103337b9103437b63232b960a11b606082015260800190565b634e487b7160e01b600052601160045260246000fd5b60008282101561151a5761151a6114f2565b500390565b83815260606020820152600061153860608301856110db565b828103604084015261154a81856110db565b9695505050505050565b60006020828403121561156657600080fd5b81516106608161113b565b6001600160a01b0387811682528681166020830152851660408201526060810184905260c0608082018190526000906115ac908301856110db565b82810360a08401526115be81856110db565b9998505050505050505050565b600082198211156115de576115de6114f2565b50019056fea2646970667358221220103aac659e4996b5bfaab3aace455bff9e91094d1540c8896ca02b96baf6cde764736f6c634300080d0033";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_AUTHORIZEOPERATOR = "authorizeOperator";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DEFAULTOPERATORS = "defaultOperators";

    public static final String FUNC_GRANULARITY = "granularity";

    public static final String FUNC_ISOPERATORFOR = "isOperatorFor";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OPERATORBURN = "operatorBurn";

    public static final String FUNC_OPERATORSEND = "operatorSend";

    public static final String FUNC_REVOKEOPERATOR = "revokeOperator";

    public static final String FUNC_SEND = "send";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event AUTHORIZEDOPERATOR_EVENT = new Event("AuthorizedOperator", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event BURNED_EVENT = new Event("Burned", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event MINTED_EVENT = new Event("Minted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event REVOKEDOPERATOR_EVENT = new Event("RevokedOperator", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event SENT_EVENT = new Event("Sent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected ERC777(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ERC777(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ERC777(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ERC777(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public static List<AuthorizedOperatorEventResponse> getAuthorizedOperatorEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(AUTHORIZEDOPERATOR_EVENT, transactionReceipt);
        ArrayList<AuthorizedOperatorEventResponse> responses = new ArrayList<AuthorizedOperatorEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AuthorizedOperatorEventResponse typedResponse = new AuthorizedOperatorEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tokenHolder = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AuthorizedOperatorEventResponse> authorizedOperatorEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AuthorizedOperatorEventResponse>() {
            @Override
            public AuthorizedOperatorEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(AUTHORIZEDOPERATOR_EVENT, log);
                AuthorizedOperatorEventResponse typedResponse = new AuthorizedOperatorEventResponse();
                typedResponse.log = log;
                typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.tokenHolder = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AuthorizedOperatorEventResponse> authorizedOperatorEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(AUTHORIZEDOPERATOR_EVENT));
        return authorizedOperatorEventFlowable(filter);
    }

    public static List<BurnedEventResponse> getBurnedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(BURNED_EVENT, transactionReceipt);
        ArrayList<BurnedEventResponse> responses = new ArrayList<BurnedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BurnedEventResponse typedResponse = new BurnedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BurnedEventResponse> burnedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, BurnedEventResponse>() {
            @Override
            public BurnedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(BURNED_EVENT, log);
                BurnedEventResponse typedResponse = new BurnedEventResponse();
                typedResponse.log = log;
                typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<BurnedEventResponse> burnedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BURNED_EVENT));
        return burnedEventFlowable(filter);
    }

    public static List<MintedEventResponse> getMintedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(MINTED_EVENT, transactionReceipt);
        ArrayList<MintedEventResponse> responses = new ArrayList<MintedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MintedEventResponse typedResponse = new MintedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MintedEventResponse> mintedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, MintedEventResponse>() {
            @Override
            public MintedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(MINTED_EVENT, log);
                MintedEventResponse typedResponse = new MintedEventResponse();
                typedResponse.log = log;
                typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MintedEventResponse> mintedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MINTED_EVENT));
        return mintedEventFlowable(filter);
    }

    public static List<RevokedOperatorEventResponse> getRevokedOperatorEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(REVOKEDOPERATOR_EVENT, transactionReceipt);
        ArrayList<RevokedOperatorEventResponse> responses = new ArrayList<RevokedOperatorEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RevokedOperatorEventResponse typedResponse = new RevokedOperatorEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tokenHolder = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RevokedOperatorEventResponse> revokedOperatorEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RevokedOperatorEventResponse>() {
            @Override
            public RevokedOperatorEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REVOKEDOPERATOR_EVENT, log);
                RevokedOperatorEventResponse typedResponse = new RevokedOperatorEventResponse();
                typedResponse.log = log;
                typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.tokenHolder = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RevokedOperatorEventResponse> revokedOperatorEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REVOKEDOPERATOR_EVENT));
        return revokedOperatorEventFlowable(filter);
    }

    public static List<SentEventResponse> getSentEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SENT_EVENT, transactionReceipt);
        ArrayList<SentEventResponse> responses = new ArrayList<SentEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SentEventResponse typedResponse = new SentEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SentEventResponse> sentEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SentEventResponse>() {
            @Override
            public SentEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SENT_EVENT, log);
                SentEventResponse typedResponse = new SentEventResponse();
                typedResponse.log = log;
                typedResponse.operator = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.from = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.data = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.operatorData = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SentEventResponse> sentEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SENT_EVENT));
        return sentEventFlowable(filter);
    }

    public static List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> allowance(String holder, String spender) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, holder), 
                new org.web3j.abi.datatypes.Address(160, spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> authorizeOperator(String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_AUTHORIZEOPERATOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String tokenHolder) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, tokenHolder)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger amount, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> defaultOperators() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DEFAULTOPERATORS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> granularity() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GRANULARITY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> isOperatorFor(String operator, String tokenHolder) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISOPERATORFOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator), 
                new org.web3j.abi.datatypes.Address(160, tokenHolder)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> operatorBurn(String account, BigInteger amount, byte[] data, byte[] operatorData) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_OPERATORBURN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.DynamicBytes(data), 
                new org.web3j.abi.datatypes.DynamicBytes(operatorData)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> operatorSend(String sender, String recipient, BigInteger amount, byte[] data, byte[] operatorData) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_OPERATORSEND, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender), 
                new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.DynamicBytes(data), 
                new org.web3j.abi.datatypes.DynamicBytes(operatorData)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> revokeOperator(String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REVOKEOPERATOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> send(String recipient, BigInteger amount, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SEND, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String recipient, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String holder, String recipient, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, holder), 
                new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ERC777 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC777(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ERC777 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC777(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ERC777 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ERC777(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ERC777 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ERC777(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ERC777> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name_, String symbol_, List<String> defaultOperators_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_), 
                new org.web3j.abi.datatypes.Utf8String(symbol_), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(defaultOperators_, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(ERC777.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<ERC777> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name_, String symbol_, List<String> defaultOperators_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_), 
                new org.web3j.abi.datatypes.Utf8String(symbol_), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(defaultOperators_, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(ERC777.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ERC777> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name_, String symbol_, List<String> defaultOperators_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_), 
                new org.web3j.abi.datatypes.Utf8String(symbol_), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(defaultOperators_, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(ERC777.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ERC777> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name_, String symbol_, List<String> defaultOperators_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_), 
                new org.web3j.abi.datatypes.Utf8String(symbol_), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(defaultOperators_, org.web3j.abi.datatypes.Address.class))));
        return deployRemoteCall(ERC777.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class AuthorizedOperatorEventResponse extends BaseEventResponse {
        public String operator;

        public String tokenHolder;
    }

    public static class BurnedEventResponse extends BaseEventResponse {
        public String operator;

        public String from;

        public BigInteger amount;

        public byte[] data;

        public byte[] operatorData;
    }

    public static class MintedEventResponse extends BaseEventResponse {
        public String operator;

        public String to;

        public BigInteger amount;

        public byte[] data;

        public byte[] operatorData;
    }

    public static class RevokedOperatorEventResponse extends BaseEventResponse {
        public String operator;

        public String tokenHolder;
    }

    public static class SentEventResponse extends BaseEventResponse {
        public String operator;

        public String from;

        public String to;

        public BigInteger amount;

        public byte[] data;

        public byte[] operatorData;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
