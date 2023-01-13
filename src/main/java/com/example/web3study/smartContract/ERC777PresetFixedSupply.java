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
public class ERC777PresetFixedSupply extends Contract {
    public static final String BINARY = "0x60806040523480156200001157600080fd5b506040516200209d3803806200209d8339810160408190526200003491620007b9565b84848482600290805190602001906200004f929190620005b2565b50815162000065906003906020850190620005b2565b5080516200007b90600490602084019062000641565b5060005b8151811015620000eb57600160056000848481518110620000a457620000a4620008dc565b6020908102919091018101516001600160a01b03168252810191909152604001600020805460ff191691151591909117905580620000e28162000908565b9150506200007f565b506040516329965a1d60e01b815230600482018190527fac7fbab5f54a3ca8194167523c6753bfeb96a445279294b6125b68cce217705460248301526044820152731820a4b7618bde71dce8cdc73aab6c95905fad24906329965a1d90606401600060405180830381600087803b1580156200016657600080fd5b505af11580156200017b573d6000803e3d6000fd5b50506040516329965a1d60e01b815230600482018190527faea199e31a596269b42cdafd93407f14436db6e4cad65417994c2eb37381e05a60248301526044820152731820a4b7618bde71dce8cdc73aab6c95905fad2492506329965a1d9150606401600060405180830381600087803b158015620001f957600080fd5b505af11580156200020e573d6000803e3d6000fd5b5050505050505062000247818360405180602001604052806000815250604051806020016040528060008152506200025260201b60201c565b505050505062000a65565b6200026284848484600162000268565b50505050565b6001600160a01b038516620002c45760405162461bcd60e51b815260206004820181905260248201527f4552433737373a206d696e7420746f20746865207a65726f206164647265737360448201526064015b60405180910390fd5b60003390508460016000828254620002dd919062000924565b90915550506001600160a01b038616600090815260208190526040812080548792906200030c90849062000924565b909155506200032490508160008888888888620003be565b856001600160a01b0316816001600160a01b03167f2fe5be0146f74c5bce36c0b80911af6c7d86ff27e89d5cfa61fc681327954e5d8787876040516200036d939291906200096d565b60405180910390a36040518581526001600160a01b038716906000907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9060200160405180910390a3505050505050565b60405163555ddc6560e11b81526001600160a01b03861660048201527fb281fc8c12954d22544db45de3159a39272895b169a852b314f9cc762e44c53b6024820152600090731820a4b7618bde71dce8cdc73aab6c95905fad249063aabbb8ca90604401602060405180830381865afa15801562000440573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190620004669190620009a6565b90506001600160a01b03811615620004e8576040516223de2960e01b81526001600160a01b038216906223de2990620004ae908b908b908b908b908b908b90600401620009cb565b600060405180830381600087803b158015620004c957600080fd5b505af1158015620004de573d6000803e3d6000fd5b5050505062000599565b811562000599576200050e866001600160a01b0316620005a360201b620007d91760201c565b15620005995760405162461bcd60e51b815260206004820152604d60248201527f4552433737373a20746f6b656e20726563697069656e7420636f6e747261637460448201527f20686173206e6f20696d706c656d656e74657220666f7220455243373737546f60648201526c1ad95b9cd49958da5c1a595b9d609a1b608482015260a401620002bb565b5050505050505050565b6001600160a01b03163b151590565b828054620005c09062000a29565b90600052602060002090601f016020900481019282620005e457600085556200062f565b82601f10620005ff57805160ff19168380011785556200062f565b828001600101855582156200062f579182015b828111156200062f57825182559160200191906001019062000612565b506200063d92915062000699565b5090565b8280548282559060005260206000209081019282156200062f579160200282015b828111156200062f57825182546001600160a01b0319166001600160a01b0390911617825560209092019160019091019062000662565b5b808211156200063d57600081556001016200069a565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f191681016001600160401b0381118282101715620006f157620006f1620006b0565b604052919050565b60005b8381101562000716578181015183820152602001620006fc565b83811115620002625750506000910152565b600082601f8301126200073a57600080fd5b81516001600160401b03811115620007565762000756620006b0565b6200076b601f8201601f1916602001620006c6565b8181528460208386010111156200078157600080fd5b62000794826020830160208701620006f9565b949350505050565b80516001600160a01b0381168114620007b457600080fd5b919050565b600080600080600060a08688031215620007d257600080fd5b85516001600160401b0380821115620007ea57600080fd5b620007f889838a0162000728565b96506020915081880151818111156200081057600080fd5b6200081e8a828b0162000728565b9650506040880151818111156200083457600080fd5b8801601f81018a136200084657600080fd5b8051828111156200085b576200085b620006b0565b8060051b92506200086e848401620006c6565b818152928201840192848101908c8511156200088957600080fd5b928501925b84841015620008b257620008a2846200079c565b825292850192908501906200088e565b80985050505050505060608601519150620008d0608087016200079c565b90509295509295909350565b634e487b7160e01b600052603260045260246000fd5b634e487b7160e01b600052601160045260246000fd5b6000600182016200091d576200091d620008f2565b5060010190565b600082198211156200093a576200093a620008f2565b500190565b6000815180845262000959816020860160208601620006f9565b601f01601f19169290920160200192915050565b8381526060602082015260006200098860608301856200093f565b82810360408401526200099c81856200093f565b9695505050505050565b600060208284031215620009b957600080fd5b620009c4826200079c565b9392505050565b6001600160a01b0387811682528681166020830152851660408201526060810184905260c06080820181905260009062000a08908301856200093f565b82810360a084015262000a1c81856200093f565b9998505050505050505050565b600181811c9082168062000a3e57607f821691505b60208210810362000a5f57634e487b7160e01b600052602260045260246000fd5b50919050565b6116288062000a756000396000f3fe608060405234801561001057600080fd5b50600436106101165760003560e01c8063959b8c3f116100a2578063d95b637111610071578063d95b63711461022b578063dd62ed3e1461023e578063fad8b32a14610277578063fc673c4f1461028a578063fe9d93031461029d57600080fd5b8063959b8c3f146101ea57806395d89b41146101fd5780639bd9bbc614610205578063a9059cbb1461021857600080fd5b806323b872dd116100e957806323b872dd14610183578063313ce56714610196578063556f0dc7146101a557806362ad1b83146101ac57806370a08231146101c157600080fd5b806306e485381461011b57806306fdde0314610139578063095ea7b31461014e57806318160ddd14610171575b600080fd5b6101236102b0565b604051610130919061109d565b60405180910390f35b610141610312565b6040516101309190611137565b61016161015c366004611162565b61039b565b6040519015158152602001610130565b6001545b604051908152602001610130565b61016161019136600461118e565b6103b3565b60405160128152602001610130565b6001610175565b6101bf6101ba366004611272565b6103f9565b005b6101756101cf366004611305565b6001600160a01b031660009081526020819052604090205490565b6101bf6101f8366004611305565b61043e565b61014161055b565b6101bf610213366004611322565b61056a565b610161610226366004611162565b61058d565b61016161023936600461137b565b6105c5565b61017561024c36600461137b565b6001600160a01b03918216600090815260086020908152604080832093909416825291909152205490565b6101bf610285366004611305565b610667565b6101bf6102983660046113b4565b610782565b6101bf6102ab366004611434565b6107ba565b6060600480548060200260200160405190810160405280929190818152602001828054801561030857602002820191906000526020600020905b81546001600160a01b031681526001909101906020018083116102ea575b5050505050905090565b6060600280546103219061147b565b80601f016020809104026020016040519081016040528092919081815260200182805461034d9061147b565b80156103085780601f1061036f57610100808354040283529160200191610308565b820191906000526020600020905b81548152906001019060200180831161037d57509395945050505050565b6000336103a98185856107e8565b5060019392505050565b6000336103c185828561090f565b6103ee8585856040518060200160405280600081525060405180602001604052806000815250600061099b565b506001949350505050565b61040333866105c5565b6104285760405162461bcd60e51b815260040161041f906114b5565b60405180910390fd5b6104378585858585600161099b565b5050505050565b6001600160a01b03811633036104a25760405162461bcd60e51b8152602060048201526024808201527f4552433737373a20617574686f72697a696e672073656c66206173206f70657260448201526330ba37b960e11b606482015260840161041f565b6001600160a01b03811660009081526005602052604090205460ff16156104f3573360009081526007602090815260408083206001600160a01b03851684529091529020805460ff19169055610522565b3360009081526006602090815260408083206001600160a01b03851684529091529020805460ff191660011790555b60405133906001600160a01b038316907ff4caeb2d6ca8932a215a353d0703c326ec2d81fc68170f320eb2ab49e9df61f990600090a350565b6060600380546103219061147b565b6105883384848460405180602001604052806000815250600161099b565b505050565b60006105bc3384846040518060200160405280600081525060405180602001604052806000815250600061099b565b50600192915050565b6000816001600160a01b0316836001600160a01b0316148061063057506001600160a01b03831660009081526005602052604090205460ff16801561063057506001600160a01b0380831660009081526007602090815260408083209387168352929052205460ff16155b8061066057506001600160a01b0380831660009081526006602090815260408083209387168352929052205460ff165b9392505050565b336001600160a01b038216036106c95760405162461bcd60e51b815260206004820152602160248201527f4552433737373a207265766f6b696e672073656c66206173206f70657261746f6044820152603960f91b606482015260840161041f565b6001600160a01b03811660009081526005602052604090205460ff161561071d573360009081526007602090815260408083206001600160a01b03851684529091529020805460ff19166001179055610749565b3360009081526006602090815260408083206001600160a01b03851684529091529020805460ff191690555b60405133906001600160a01b038316907f50546e66e5f44d728365dc3908c63bc5cfeeab470722c1677e3073a6ac294aa190600090a350565b61078c33856105c5565b6107a85760405162461bcd60e51b815260040161041f906114b5565b6107b484848484610a97565b50505050565b6107d533838360405180602001604052806000815250610a97565b5050565b6001600160a01b03163b151590565b6001600160a01b03831661084c5760405162461bcd60e51b815260206004820152602560248201527f4552433737373a20617070726f76652066726f6d20746865207a65726f206164604482015264647265737360d81b606482015260840161041f565b6001600160a01b0382166108ae5760405162461bcd60e51b815260206004820152602360248201527f4552433737373a20617070726f766520746f20746865207a65726f206164647260448201526265737360e81b606482015260840161041f565b6001600160a01b0383811660008181526008602090815260408083209487168084529482529182902085905590518481527f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925910160405180910390a3505050565b6001600160a01b0383811660009081526008602090815260408083209386168352929052205460001981146107b4578181101561098e5760405162461bcd60e51b815260206004820152601e60248201527f4552433737373a20696e73756666696369656e7420616c6c6f77616e63650000604482015260640161041f565b6107b484848484036107e8565b6001600160a01b038616610a005760405162461bcd60e51b815260206004820152602660248201527f4552433737373a207472616e736665722066726f6d20746865207a65726f206160448201526564647265737360d01b606482015260840161041f565b6001600160a01b038516610a625760405162461bcd60e51b8152602060048201526024808201527f4552433737373a207472616e7366657220746f20746865207a65726f206164646044820152637265737360e01b606482015260840161041f565b33610a71818888888888610c4b565b610a7f818888888888610d72565b610a8e81888888888888610ed8565b50505050505050565b6001600160a01b038416610af85760405162461bcd60e51b815260206004820152602260248201527f4552433737373a206275726e2066726f6d20746865207a65726f206164647265604482015261737360f01b606482015260840161041f565b33610b0881866000878787610c4b565b6001600160a01b03851660009081526020819052604090205484811015610b7d5760405162461bcd60e51b815260206004820152602360248201527f4552433737373a206275726e20616d6f756e7420657863656564732062616c616044820152626e636560e81b606482015260840161041f565b6001600160a01b0386166000908152602081905260408120868303905560018054879290610bac908490611517565b92505081905550856001600160a01b0316826001600160a01b03167fa78a9be3a7b862d26933ad85fb11d80ef66b8f972d7cbba06621d583943a4098878787604051610bfa9392919061152e565b60405180910390a36040518581526000906001600160a01b038816907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9060200160405180910390a3505050505050565b60405163555ddc6560e11b81526001600160a01b03861660048201527f29ddb589b1fb5fc7cf394961c1adf5f8c6454761adf795e67fe149f658abe8956024820152600090731820a4b7618bde71dce8cdc73aab6c95905fad249063aabbb8ca90604401602060405180830381865afa158015610ccc573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610cf09190611563565b90506001600160a01b03811615610a8e57604051633ad5cbc160e11b81526001600160a01b038216906375ab978290610d37908a908a908a908a908a908a90600401611580565b600060405180830381600087803b158015610d5157600080fd5b505af1158015610d65573d6000803e3d6000fd5b5050505050505050505050565b6001600160a01b03851660009081526020819052604090205483811015610deb5760405162461bcd60e51b815260206004820152602760248201527f4552433737373a207472616e7366657220616d6f756e7420657863656564732060448201526662616c616e636560c81b606482015260840161041f565b6001600160a01b03808716600090815260208190526040808220878503905591871681529081208054869290610e229084906115da565b92505081905550846001600160a01b0316866001600160a01b0316886001600160a01b03167f06b541ddaa720db2b10a4d0cdac39b8d360425fc073085fac19bc82614677987878787604051610e7a9392919061152e565b60405180910390a4846001600160a01b0316866001600160a01b03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef86604051610ec791815260200190565b60405180910390a350505050505050565b60405163555ddc6560e11b81526001600160a01b03861660048201527fb281fc8c12954d22544db45de3159a39272895b169a852b314f9cc762e44c53b6024820152600090731820a4b7618bde71dce8cdc73aab6c95905fad249063aabbb8ca90604401602060405180830381865afa158015610f59573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610f7d9190611563565b90506001600160a01b03811615610ff9576040516223de2960e01b81526001600160a01b038216906223de2990610fc2908b908b908b908b908b908b90600401611580565b600060405180830381600087803b158015610fdc57600080fd5b505af1158015610ff0573d6000803e3d6000fd5b50505050611093565b8115611093576001600160a01b0386163b156110935760405162461bcd60e51b815260206004820152604d60248201527f4552433737373a20746f6b656e20726563697069656e7420636f6e747261637460448201527f20686173206e6f20696d706c656d656e74657220666f7220455243373737546f60648201526c1ad95b9cd49958da5c1a595b9d609a1b608482015260a40161041f565b5050505050505050565b6020808252825182820181905260009190848201906040850190845b818110156110de5783516001600160a01b0316835292840192918401916001016110b9565b50909695505050505050565b6000815180845260005b81811015611110576020818501810151868301820152016110f4565b81811115611122576000602083870101525b50601f01601f19169290920160200192915050565b60208152600061066060208301846110ea565b6001600160a01b038116811461115f57600080fd5b50565b6000806040838503121561117557600080fd5b82356111808161114a565b946020939093013593505050565b6000806000606084860312156111a357600080fd5b83356111ae8161114a565b925060208401356111be8161114a565b929592945050506040919091013590565b634e487b7160e01b600052604160045260246000fd5b600082601f8301126111f657600080fd5b813567ffffffffffffffff80821115611211576112116111cf565b604051601f8301601f19908116603f01168101908282118183101715611239576112396111cf565b8160405283815286602085880101111561125257600080fd5b836020870160208301376000602085830101528094505050505092915050565b600080600080600060a0868803121561128a57600080fd5b85356112958161114a565b945060208601356112a58161114a565b935060408601359250606086013567ffffffffffffffff808211156112c957600080fd5b6112d589838a016111e5565b935060808801359150808211156112eb57600080fd5b506112f8888289016111e5565b9150509295509295909350565b60006020828403121561131757600080fd5b81356106608161114a565b60008060006060848603121561133757600080fd5b83356113428161114a565b925060208401359150604084013567ffffffffffffffff81111561136557600080fd5b611371868287016111e5565b9150509250925092565b6000806040838503121561138e57600080fd5b82356113998161114a565b915060208301356113a98161114a565b809150509250929050565b600080600080608085870312156113ca57600080fd5b84356113d58161114a565b935060208501359250604085013567ffffffffffffffff808211156113f957600080fd5b611405888389016111e5565b9350606087013591508082111561141b57600080fd5b50611428878288016111e5565b91505092959194509250565b6000806040838503121561144757600080fd5b82359150602083013567ffffffffffffffff81111561146557600080fd5b611471858286016111e5565b9150509250929050565b600181811c9082168061148f57607f821691505b6020821081036114af57634e487b7160e01b600052602260045260246000fd5b50919050565b6020808252602c908201527f4552433737373a2063616c6c6572206973206e6f7420616e206f70657261746f60408201526b39103337b9103437b63232b960a11b606082015260800190565b634e487b7160e01b600052601160045260246000fd5b60008282101561152957611529611501565b500390565b83815260606020820152600061154760608301856110ea565b828103604084015261155981856110ea565b9695505050505050565b60006020828403121561157557600080fd5b81516106608161114a565b6001600160a01b0387811682528681166020830152851660408201526060810184905260c0608082018190526000906115bb908301856110ea565b82810360a08401526115cd81856110ea565b9998505050505050505050565b600082198211156115ed576115ed611501565b50019056fea2646970667358221220e1eef4b9ec39e98e9d03db728c501b26e96065159e2a89f57217126dc1c5e7fc64736f6c634300080d0033";

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
    protected ERC777PresetFixedSupply(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ERC777PresetFixedSupply(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ERC777PresetFixedSupply(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ERC777PresetFixedSupply(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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
    public static ERC777PresetFixedSupply load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC777PresetFixedSupply(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ERC777PresetFixedSupply load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ERC777PresetFixedSupply(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ERC777PresetFixedSupply load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ERC777PresetFixedSupply(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ERC777PresetFixedSupply load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ERC777PresetFixedSupply(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ERC777PresetFixedSupply> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name, String symbol, List<String> defaultOperators, BigInteger initialSupply, String owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(defaultOperators, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(initialSupply), 
                new org.web3j.abi.datatypes.Address(160, owner)));
        return deployRemoteCall(ERC777PresetFixedSupply.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<ERC777PresetFixedSupply> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name, String symbol, List<String> defaultOperators, BigInteger initialSupply, String owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(defaultOperators, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(initialSupply), 
                new org.web3j.abi.datatypes.Address(160, owner)));
        return deployRemoteCall(ERC777PresetFixedSupply.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ERC777PresetFixedSupply> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol, List<String> defaultOperators, BigInteger initialSupply, String owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(defaultOperators, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(initialSupply), 
                new org.web3j.abi.datatypes.Address(160, owner)));
        return deployRemoteCall(ERC777PresetFixedSupply.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ERC777PresetFixedSupply> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol, List<String> defaultOperators, BigInteger initialSupply, String owner) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.datatypes.Address.class,
                        org.web3j.abi.Utils.typeMap(defaultOperators, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(initialSupply), 
                new org.web3j.abi.datatypes.Address(160, owner)));
        return deployRemoteCall(ERC777PresetFixedSupply.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
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
