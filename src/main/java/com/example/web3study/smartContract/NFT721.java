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
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
public class NFT721 extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b50604051620023083803806200230883398101604081905262000034916200079c565b8351849084906200004d90600090602085019062000620565b5080516200006390600190602084019062000620565b505050620000806200007a6200017660201b60201c565b6200017a565b6000845111620000d75760405162461bcd60e51b815260206004820152601f60248201527f546865206e616d65206f66206e66742063616e6e6f7420626520656d7074790060448201526064015b60405180910390fd5b8051620000ec90600790602084019062000620565b50620001046008620001cc60201b620009b41760201c565b81156200016c5760015b8281116200016a5760006200012f6008620001d560201b620009bd1760201c565b9050620001486008620001cc60201b620009b41760201c565b620001543382620001d9565b508062000161816200084d565b9150506200010e565b505b5050505062000966565b3390565b600680546001600160a01b038381166001600160a01b0319831681179093556040519116919082907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e090600090a35050565b80546001019055565b5490565b620001fb828260405180602001604052806000815250620001ff60201b60201c565b5050565b6200020b838362000277565b6200021a600084848462000418565b620002725760405162461bcd60e51b81526020600482015260326024820152600080516020620022e883398151915260448201527131b2b4bb32b91034b6b83632b6b2b73a32b960711b6064820152608401620000ce565b505050565b6001600160a01b038216620002cf5760405162461bcd60e51b815260206004820181905260248201527f4552433732313a206d696e7420746f20746865207a65726f20616464726573736044820152606401620000ce565b6000818152600260205260409020546001600160a01b031615620003365760405162461bcd60e51b815260206004820152601c60248201527f4552433732313a20746f6b656e20616c7265616479206d696e746564000000006044820152606401620000ce565b6200034660008383600162000581565b6000818152600260205260409020546001600160a01b031615620003ad5760405162461bcd60e51b815260206004820152601c60248201527f4552433732313a20746f6b656e20616c7265616479206d696e746564000000006044820152606401620000ce565b6001600160a01b038216600081815260036020908152604080832080546001019055848352600290915280822080546001600160a01b0319168417905551839291907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef908290a45050565b600062000439846001600160a01b03166200061160201b620009c11760201c565b156200057557604051630a85bd0160e11b81526001600160a01b0385169063150b7a0290620004739033908990889088906004016200086b565b602060405180830381600087803b1580156200048e57600080fd5b505af1925050508015620004c1575060408051601f3d908101601f19168201909252620004be91810190620008c1565b60015b6200055a573d808015620004f2576040519150601f19603f3d011682016040523d82523d6000602084013e620004f7565b606091505b508051620005525760405162461bcd60e51b81526020600482015260326024820152600080516020620022e883398151915260448201527131b2b4bb32b91034b6b83632b6b2b73a32b960711b6064820152608401620000ce565b805181602001fd5b6001600160e01b031916630a85bd0160e11b14905062000579565b5060015b949350505050565b60018111156200060b576001600160a01b03841615620005cb576001600160a01b03841660009081526003602052604081208054839290620005c5908490620008f4565b90915550505b6001600160a01b038316156200060b576001600160a01b03831660009081526003602052604081208054839290620006059084906200090e565b90915550505b50505050565b6001600160a01b03163b151590565b8280546200062e9062000929565b90600052602060002090601f0160209004810192826200065257600085556200069d565b82601f106200066d57805160ff19168380011785556200069d565b828001600101855582156200069d579182015b828111156200069d57825182559160200191906001019062000680565b50620006ab929150620006af565b5090565b5b80821115620006ab5760008155600101620006b0565b634e487b7160e01b600052604160045260246000fd5b60005b83811015620006f9578181015183820152602001620006df565b838111156200060b5750506000910152565b600082601f8301126200071d57600080fd5b81516001600160401b03808211156200073a576200073a620006c6565b604051601f8301601f19908116603f01168101908282118183101715620007655762000765620006c6565b816040528381528660208588010111156200077f57600080fd5b62000792846020830160208901620006dc565b9695505050505050565b60008060008060808587031215620007b357600080fd5b84516001600160401b0380821115620007cb57600080fd5b620007d9888389016200070b565b95506020870151915080821115620007f057600080fd5b620007fe888389016200070b565b94506040870151935060608701519150808211156200081c57600080fd5b506200082b878288016200070b565b91505092959194509250565b634e487b7160e01b600052601160045260246000fd5b600060001982141562000864576200086462000837565b5060010190565b600060018060a01b038087168352808616602084015250836040830152608060608301528251806080840152620008aa8160a0850160208701620006dc565b601f01601f19169190910160a00195945050505050565b600060208284031215620008d457600080fd5b81516001600160e01b031981168114620008ed57600080fd5b9392505050565b60008282101562000909576200090962000837565b500390565b6000821982111562000924576200092462000837565b500190565b600181811c908216806200093e57607f821691505b602082108114156200096057634e487b7160e01b600052602260045260246000fd5b50919050565b61197280620009766000396000f3fe608060405234801561001057600080fd5b506004361061014d5760003560e01c80636871ee40116100c3578063b88d4fde1161007c578063b88d4fde146102b1578063c610036f146102c4578063c87b56dd146102d7578063e985e9c5146102ea578063f2fde38b14610326578063fee467311461033957600080fd5b80636871ee401461025457806370a082311461025c578063715018a61461027d5780638da5cb5b1461028557806395d89b4114610296578063a22cb4651461029e57600080fd5b80631d15e2c7116101155780631d15e2c7146101e257806323b872dd146101f557806327facdd21461020857806342842e0e1461021b57806342966c681461022e5780636352211e1461024157600080fd5b806301e314e11461015257806301ffc9a71461016757806306fdde031461018f578063081812fc146101a4578063095ea7b3146101cf575b600080fd5b61016561016036600461149c565b61034c565b005b61017a610175366004611509565b6103e3565b60405190151581526020015b60405180910390f35b610197610435565b604051610186919061157e565b6101b76101b2366004611591565b6104c7565b6040516001600160a01b039091168152602001610186565b6101656101dd3660046115aa565b6104ee565b6101656101f03660046115d4565b610604565b6101656102033660046115ef565b610635565b610165610216366004611591565b610667565b6101656102293660046115ef565b6106b8565b61016561023c366004611591565b6106d3565b6101b761024f366004611591565b610704565b610165610764565b61026f61026a3660046115d4565b610783565b604051908152602001610186565b610165610809565b6006546001600160a01b03166101b7565b61019761081b565b6101656102ac36600461162b565b61082a565b6101656102bf366004611667565b610835565b6101656102d2366004611591565b610867565b6101976102e5366004611591565b6108b5565b61017a6102f83660046116cf565b6001600160a01b03918216600090815260056020908152604080832093909416825291909152205460ff1690565b6101656103343660046115d4565b61091c565b6101656103473660046115aa565b610992565b6103546109d0565b60008281526009602052604090205460ff16156103c45760405162461bcd60e51b815260206004820152602360248201527f4552433732313a205573657220646f6573206e6f7420616c6c6f77207472616e60448201526273666560e81b60648201526084015b60405180910390fd5b60006103cf83610704565b90506103dd81858585610a2a565b50505050565b60006001600160e01b031982166380ac58cd60e01b148061041457506001600160e01b03198216635b5e139f60e01b145b8061042f57506301ffc9a760e01b6001600160e01b03198316145b92915050565b60606000805461044490611702565b80601f016020809104026020016040519081016040528092919081815260200182805461047090611702565b80156104bd5780601f10610492576101008083540402835291602001916104bd565b820191906000526020600020905b8154815290600101906020018083116104a057829003601f168201915b5050505050905090565b60006104d282610a5d565b506000908152600460205260409020546001600160a01b031690565b60006104f982610704565b9050806001600160a01b0316836001600160a01b031614156105675760405162461bcd60e51b815260206004820152602160248201527f4552433732313a20617070726f76616c20746f2063757272656e74206f776e656044820152603960f91b60648201526084016103bb565b336001600160a01b0382161480610583575061058381336102f8565b6105f55760405162461bcd60e51b815260206004820152603d60248201527f4552433732313a20617070726f76652063616c6c6572206973206e6f7420746f60448201527f6b656e206f776e6572206f7220617070726f76656420666f7220616c6c00000060648201526084016103bb565b6105ff8383610abc565b505050565b61060c6109d0565b600061061760085490565b9050610627600880546001019055565b6106318282610b2a565b5050565b610640335b82610b44565b61065c5760405162461bcd60e51b81526004016103bb9061173d565b6105ff838383610bc3565b600061067282610704565b9050336001600160a01b0382161461069c5760405162461bcd60e51b81526004016103bb9061178a565b506000908152600960205260409020805460ff19166001179055565b6105ff83838360405180602001604052806000815250610835565b6106dc3361063a565b6106f85760405162461bcd60e51b81526004016103bb9061173d565b61070181610d34565b50565b6000818152600260205260408120546001600160a01b03168061042f5760405162461bcd60e51b8152602060048201526018602482015277115490cdcc8c4e881a5b9d985b1a59081d1bdad95b88125160421b60448201526064016103bb565b61076c6109d0565b6107816101f06006546001600160a01b031690565b565b60006001600160a01b0382166107ed5760405162461bcd60e51b815260206004820152602960248201527f4552433732313a2061646472657373207a65726f206973206e6f7420612076616044820152683634b21037bbb732b960b91b60648201526084016103bb565b506001600160a01b031660009081526003602052604090205490565b6108116109d0565b6107816000610dd7565b60606001805461044490611702565b610631338383610e29565b61083f3383610b44565b61085b5760405162461bcd60e51b81526004016103bb9061173d565b6103dd84848484610a2a565b600061087282610704565b9050336001600160a01b0382161461089c5760405162461bcd60e51b81526004016103bb9061178a565b506000908152600960205260409020805460ff19169055565b60606108c082610a5d565b60006108ca610ef8565b905060008151116108ea5760405180602001604052806000815250610915565b806108f484610f07565b6040516020016109059291906117d7565b6040516020818303038152906040525b9392505050565b6109246109d0565b6001600160a01b0381166109895760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b60648201526084016103bb565b61070181610dd7565b61099a6109d0565b61063182826040518060200160405280600081525061034c565b80546001019055565b5490565b6001600160a01b03163b151590565b6006546001600160a01b031633146107815760405162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e657260448201526064016103bb565b610a35848484610bc3565b610a4184848484610fa4565b6103dd5760405162461bcd60e51b81526004016103bb90611806565b6000818152600260205260409020546001600160a01b03166107015760405162461bcd60e51b8152602060048201526018602482015277115490cdcc8c4e881a5b9d985b1a59081d1bdad95b88125160421b60448201526064016103bb565b600081815260046020526040902080546001600160a01b0319166001600160a01b0384169081179091558190610af182610704565b6001600160a01b03167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92560405160405180910390a45050565b6106318282604051806020016040528060008152506110b1565b600080610b5083610704565b9050806001600160a01b0316846001600160a01b03161480610b9757506001600160a01b0380821660009081526005602090815260408083209388168352929052205460ff165b80610bbb5750836001600160a01b0316610bb0846104c7565b6001600160a01b0316145b949350505050565b826001600160a01b0316610bd682610704565b6001600160a01b031614610bfc5760405162461bcd60e51b81526004016103bb90611858565b6001600160a01b038216610c5e5760405162461bcd60e51b8152602060048201526024808201527f4552433732313a207472616e7366657220746f20746865207a65726f206164646044820152637265737360e01b60648201526084016103bb565b610c6b83838360016110e4565b826001600160a01b0316610c7e82610704565b6001600160a01b031614610ca45760405162461bcd60e51b81526004016103bb90611858565b600081815260046020908152604080832080546001600160a01b03199081169091556001600160a01b0387811680865260038552838620805460001901905590871680865283862080546001019055868652600290945282852080549092168417909155905184937fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef91a4505050565b6000610d3f82610704565b9050610d4f8160008460016110e4565b610d5882610704565b600083815260046020908152604080832080546001600160a01b03199081169091556001600160a01b0385168085526003845282852080546000190190558785526002909352818420805490911690555192935084927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef908390a45050565b600680546001600160a01b038381166001600160a01b0319831681179093556040519116919082907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e090600090a35050565b816001600160a01b0316836001600160a01b03161415610e8b5760405162461bcd60e51b815260206004820152601960248201527f4552433732313a20617070726f766520746f2063616c6c65720000000000000060448201526064016103bb565b6001600160a01b03838116600081815260056020908152604080832094871680845294825291829020805460ff191686151590811790915591519182527f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c31910160405180910390a3505050565b60606007805461044490611702565b60606000610f148361116c565b600101905060008167ffffffffffffffff811115610f3457610f346113f9565b6040519080825280601f01601f191660200182016040528015610f5e576020820181803683370190505b5090508181016020015b600019016f181899199a1a9b1b9c1cb0b131b232b360811b600a86061a8153600a8504945084610f9757610f9c565b610f68565b509392505050565b60006001600160a01b0384163b156110a657604051630a85bd0160e11b81526001600160a01b0385169063150b7a0290610fe890339089908890889060040161189d565b602060405180830381600087803b15801561100257600080fd5b505af1925050508015611032575060408051601f3d908101601f1916820190925261102f918101906118da565b60015b61108c573d808015611060576040519150601f19603f3d011682016040523d82523d6000602084013e611065565b606091505b5080516110845760405162461bcd60e51b81526004016103bb90611806565b805181602001fd5b6001600160e01b031916630a85bd0160e11b149050610bbb565b506001949350505050565b6110bb8383611244565b6110c86000848484610fa4565b6105ff5760405162461bcd60e51b81526004016103bb90611806565b60018111156103dd576001600160a01b0384161561112a576001600160a01b0384166000908152600360205260408120805483929061112490849061190d565b90915550505b6001600160a01b038316156103dd576001600160a01b03831660009081526003602052604081208054839290611161908490611924565b909155505050505050565b60008072184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b83106111ab5772184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b830492506040015b6d04ee2d6d415b85acef810000000083106111d7576d04ee2d6d415b85acef8100000000830492506020015b662386f26fc1000083106111f557662386f26fc10000830492506010015b6305f5e100831061120d576305f5e100830492506008015b612710831061122157612710830492506004015b60648310611233576064830492506002015b600a831061042f5760010192915050565b6001600160a01b03821661129a5760405162461bcd60e51b815260206004820181905260248201527f4552433732313a206d696e7420746f20746865207a65726f206164647265737360448201526064016103bb565b6000818152600260205260409020546001600160a01b0316156112ff5760405162461bcd60e51b815260206004820152601c60248201527f4552433732313a20746f6b656e20616c7265616479206d696e7465640000000060448201526064016103bb565b61130d6000838360016110e4565b6000818152600260205260409020546001600160a01b0316156113725760405162461bcd60e51b815260206004820152601c60248201527f4552433732313a20746f6b656e20616c7265616479206d696e7465640000000060448201526064016103bb565b6001600160a01b038216600081815260036020908152604080832080546001019055848352600290915280822080546001600160a01b0319168417905551839291907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef908290a45050565b80356001600160a01b03811681146113f457600080fd5b919050565b634e487b7160e01b600052604160045260246000fd5b600082601f83011261142057600080fd5b813567ffffffffffffffff8082111561143b5761143b6113f9565b604051601f8301601f19908116603f01168101908282118183101715611463576114636113f9565b8160405283815286602085880101111561147c57600080fd5b836020870160208301376000602085830101528094505050505092915050565b6000806000606084860312156114b157600080fd5b6114ba846113dd565b925060208401359150604084013567ffffffffffffffff8111156114dd57600080fd5b6114e98682870161140f565b9150509250925092565b6001600160e01b03198116811461070157600080fd5b60006020828403121561151b57600080fd5b8135610915816114f3565b60005b83811015611541578181015183820152602001611529565b838111156103dd5750506000910152565b6000815180845261156a816020860160208601611526565b601f01601f19169290920160200192915050565b6020815260006109156020830184611552565b6000602082840312156115a357600080fd5b5035919050565b600080604083850312156115bd57600080fd5b6115c6836113dd565b946020939093013593505050565b6000602082840312156115e657600080fd5b610915826113dd565b60008060006060848603121561160457600080fd5b61160d846113dd565b925061161b602085016113dd565b9150604084013590509250925092565b6000806040838503121561163e57600080fd5b611647836113dd565b91506020830135801515811461165c57600080fd5b809150509250929050565b6000806000806080858703121561167d57600080fd5b611686856113dd565b9350611694602086016113dd565b925060408501359150606085013567ffffffffffffffff8111156116b757600080fd5b6116c38782880161140f565b91505092959194509250565b600080604083850312156116e257600080fd5b6116eb836113dd565b91506116f9602084016113dd565b90509250929050565b600181811c9082168061171657607f821691505b6020821081141561173757634e487b7160e01b600052602260045260246000fd5b50919050565b6020808252602d908201527f4552433732313a2063616c6c6572206973206e6f7420746f6b656e206f776e6560408201526c1c881bdc88185c1c1c9bdd9959609a1b606082015260800190565b6020808252602d908201527f4552433732313a20596f7520617265206e6f7420746865206f776e6572206f6660408201526c081d1a194818dbdb9d1c9858dd609a1b606082015260800190565b600083516117e9818460208801611526565b8351908301906117fd818360208801611526565b01949350505050565b60208082526032908201527f4552433732313a207472616e7366657220746f206e6f6e20455243373231526560408201527131b2b4bb32b91034b6b83632b6b2b73a32b960711b606082015260800190565b60208082526025908201527f4552433732313a207472616e736665722066726f6d20696e636f72726563742060408201526437bbb732b960d91b606082015260800190565b6001600160a01b03858116825284166020820152604081018390526080606082018190526000906118d090830184611552565b9695505050505050565b6000602082840312156118ec57600080fd5b8151610915816114f3565b634e487b7160e01b600052601160045260246000fd5b60008282101561191f5761191f6118f7565b500390565b60008219821115611937576119376118f7565b50019056fea26469706673582212202a35cdf72f86f192ddec0fd5ec1fa465f4eecceb8e5b0121cdb8b2a07a65928664736f6c634300080900334552433732313a207472616e7366657220746f206e6f6e204552433732315265";

    public static final String FUNC_APPROVALTOADMIN = "ApprovalToAdmin";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_NOTAPPROVALTOADMIN = "notApprovalToAdmin";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_OWNEROF = "ownerOf";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SAFEMINT = "safeMint";

    public static final String FUNC_SAFEMINTTOADDRESS = "safeMintToAddress";

    public static final String FUNC_safeTransferFrom = "safeTransferFrom";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOKENURI = "tokenURI";

    public static final String FUNC_transferByAdmin = "transferByAdmin";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected NFT721(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected NFT721(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected NFT721(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public NFT721(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
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
                typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public static List<ApprovalForAllEventResponse> getApprovalForAllEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalForAllEventResponse>() {
            @Override
            public ApprovalForAllEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
                ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
        return approvalForAllEventFlowable(filter);
    }

    public static List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public static List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
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
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> ApprovalToAdmin(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVALTOADMIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getApproved(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPROVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> isApprovedForAll(String owner, String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, operator)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> notApprovalToAdmin(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_NOTAPPROVALTOADMIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> ownerOf(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNEROF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeMint() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SAFEMINT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeMintToAddress(String to) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SAFEMINTTOADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String operator, Boolean approved) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator), 
                new org.web3j.abi.datatypes.Bool(approved)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> tokenURI(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENURI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferByAdmin(String to, BigInteger tokenId, byte[] data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_transferByAdmin, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferByAdmin(String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_transferByAdmin, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static NFT721 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFT721(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static NFT721 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFT721(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static NFT721 load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new NFT721(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static NFT721 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new NFT721(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<NFT721> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name, String symbol, BigInteger mint_number, String url) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.generated.Uint256(mint_number), 
                new org.web3j.abi.datatypes.Utf8String(url)));
        return deployRemoteCall(NFT721.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<NFT721> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name, String symbol, BigInteger mint_number, String url) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.generated.Uint256(mint_number), 
                new org.web3j.abi.datatypes.Utf8String(url)));
        return deployRemoteCall(NFT721.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<NFT721> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol, BigInteger mint_number, String url) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.generated.Uint256(mint_number), 
                new org.web3j.abi.datatypes.Utf8String(url)));
        return deployRemoteCall(NFT721.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<NFT721> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol, BigInteger mint_number, String url) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.generated.Uint256(mint_number), 
                new org.web3j.abi.datatypes.Utf8String(url)));
        return deployRemoteCall(NFT721.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String approved;

        public BigInteger tokenId;
    }

    public static class ApprovalForAllEventResponse extends BaseEventResponse {
        public String owner;

        public String operator;

        public Boolean approved;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger tokenId;
    }
}
