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
public class MyToken extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b50604051620026d7380380620026d783398101604081905262000034916200078f565b8351849084906200004d90600090602085019062000613565b5080516200006390600190602084019062000613565b505050620000806200007a6200017660201b60201c565b6200017a565b6000845111620000d75760405162461bcd60e51b815260206004820152601e60248201527f546865206e616d65206f66206e66742063616e6e6f7420626520656d7074000060448201526064015b60405180910390fd5b8051620000ec90600790602084019062000613565b50620001046009620001cc60201b62000d3d1760201c565b81156200016c5760015b8281116200016a5760006200012f6009620001d560201b62000d461760201c565b9050620001486009620001cc60201b62000d3d1760201c565b620001543382620001d9565b5080620001618162000840565b9150506200010e565b505b5050505062000956565b3390565b600680546001600160a01b038381166001600160a01b0319831681179093556040519116919082907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e090600090a35050565b80546001019055565b5490565b620001fb828260405180602001604052806000815250620001ff60201b60201c565b5050565b6200020b838362000277565b6200021a600084848462000418565b620002725760405162461bcd60e51b81526020600482015260326024820152600080516020620026b783398151915260448201527131b2b4bb32b91034b6b83632b6b2b73a32b960711b6064820152608401620000ce565b505050565b6001600160a01b038216620002cf5760405162461bcd60e51b815260206004820181905260248201527f4552433732313a206d696e7420746f20746865207a65726f20616464726573736044820152606401620000ce565b6000818152600260205260409020546001600160a01b031615620003365760405162461bcd60e51b815260206004820152601c60248201527f4552433732313a20746f6b656e20616c7265616479206d696e746564000000006044820152606401620000ce565b6200034660008383600162000574565b6000818152600260205260409020546001600160a01b031615620003ad5760405162461bcd60e51b815260206004820152601c60248201527f4552433732313a20746f6b656e20616c7265616479206d696e746564000000006044820152606401620000ce565b6001600160a01b038216600081815260036020908152604080832080546001019055848352600290915280822080546001600160a01b0319168417905551839291907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef908290a45050565b600062000439846001600160a01b03166200060460201b62000d4a1760201c565b156200056857604051630a85bd0160e11b81526001600160a01b0385169063150b7a0290620004739033908990889088906004016200085c565b6020604051808303816000875af1925050508015620004b1575060408051601f3d908101601f19168201909252620004ae91810190620008b2565b60015b6200054d573d808015620004e2576040519150601f19603f3d011682016040523d82523d6000602084013e620004e7565b606091505b508051600003620005455760405162461bcd60e51b81526020600482015260326024820152600080516020620026b783398151915260448201527131b2b4bb32b91034b6b83632b6b2b73a32b960711b6064820152608401620000ce565b805181602001fd5b6001600160e01b031916630a85bd0160e11b1490506200056c565b5060015b949350505050565b6001811115620005fe576001600160a01b03841615620005be576001600160a01b03841660009081526003602052604081208054839290620005b8908490620008e5565b90915550505b6001600160a01b03831615620005fe576001600160a01b03831660009081526003602052604081208054839290620005f8908490620008ff565b90915550505b50505050565b6001600160a01b03163b151590565b82805462000621906200091a565b90600052602060002090601f01602090048101928262000645576000855562000690565b82601f106200066057805160ff191683800117855562000690565b8280016001018555821562000690579182015b828111156200069057825182559160200191906001019062000673565b506200069e929150620006a2565b5090565b5b808211156200069e5760008155600101620006a3565b634e487b7160e01b600052604160045260246000fd5b60005b83811015620006ec578181015183820152602001620006d2565b83811115620005fe5750506000910152565b600082601f8301126200071057600080fd5b81516001600160401b03808211156200072d576200072d620006b9565b604051601f8301601f19908116603f01168101908282118183101715620007585762000758620006b9565b816040528381528660208588010111156200077257600080fd5b62000785846020830160208901620006cf565b9695505050505050565b60008060008060808587031215620007a657600080fd5b84516001600160401b0380821115620007be57600080fd5b620007cc88838901620006fe565b95506020870151915080821115620007e357600080fd5b620007f188838901620006fe565b94506040870151935060608701519150808211156200080f57600080fd5b506200081e87828801620006fe565b91505092959194509250565b634e487b7160e01b600052601160045260246000fd5b6000600182016200085557620008556200082a565b5060010190565b600060018060a01b0380871683528086166020840152508360408301526080606083015282518060808401526200089b8160a0850160208701620006cf565b601f01601f19169190910160a00195945050505050565b600060208284031215620008c557600080fd5b81516001600160e01b031981168114620008de57600080fd5b9392505050565b600082821015620008fa57620008fa6200082a565b500390565b600082198211156200091557620009156200082a565b500190565b600181811c908216806200092f57607f821691505b6020821081036200095057634e487b7160e01b600052602260045260246000fd5b50919050565b611d5180620009666000396000f3fe608060405234801561001057600080fd5b506004361061018e5760003560e01c80636871ee40116100de5780639b819d3811610097578063c87b56dd11610071578063c87b56dd14610344578063e985e9c514610357578063f2fde38b14610393578063fee46731146103a657600080fd5b80639b819d3814610318578063a22cb4651461031e578063b88d4fde1461033157600080fd5b80636871ee40146102bb57806370a08231146102c3578063715018a6146102e45780637faef7b1146102ec5780638da5cb5b146102ff57806395d89b411461031057600080fd5b806316c3b3741161014b578063273684b311610125578063273684b31461026f57806342842e0e1461028257806342966c68146102955780636352211e146102a857600080fd5b806316c3b374146102365780631d15e2c71461024957806323b872dd1461025c57600080fd5b806301e314e11461019357806301ffc9a7146101a857806306fdde03146101d0578063081812fc146101e5578063095ea7b31461021057806316b94b7114610223575b600080fd5b6101a66101a1366004611807565b6103b9565b005b6101bb6101b6366004611876565b610450565b60405190151581526020015b60405180910390f35b6101d86104a2565b6040516101c791906118eb565b6101f86101f33660046118fe565b610534565b6040516001600160a01b0390911681526020016101c7565b6101a661021e366004611917565b61055b565b6101a6610231366004611958565b610670565b6101a6610244366004611984565b61070b565b6101a6610257366004611984565b610821565b6101a661026a3660046119a1565b610852565b6101a661027d3660046119e2565b610884565b6101a66102903660046119a1565b610a8f565b6101a66102a33660046118fe565b610aaa565b6101f86102b63660046118fe565b610adb565b6101a6610b3b565b6102d66102d1366004611984565b610b5a565b6040519081526020016101c7565b6101a6610be0565b6008546101f8906001600160a01b031681565b6006546001600160a01b03166101f8565b6101d8610bf2565b426102d6565b6101a661032c366004611a28565b610c01565b6101a661033f366004611a54565b610c0c565b6101d86103523660046118fe565b610c3e565b6101bb610365366004611ac0565b6001600160a01b03918216600090815260056020908152604080832093909416825291909152205460ff1690565b6101a66103a1366004611984565b610ca5565b6101a66103b4366004611917565b610d1b565b6103c1610d59565b6000828152600a602052604090205460ff16156104315760405162461bcd60e51b815260206004820152602360248201527f4552433732313a205573657220646f6573206e6f7420616c6c6f77207472616e60448201526273666560e81b60648201526084015b60405180910390fd5b600061043c83610adb565b905061044a81858585610db3565b50505050565b60006001600160e01b031982166380ac58cd60e01b148061048157506001600160e01b03198216635b5e139f60e01b145b8061049c57506301ffc9a760e01b6001600160e01b03198316145b92915050565b6060600080546104b190611af9565b80601f01602080910402602001604051908101604052809291908181526020018280546104dd90611af9565b801561052a5780601f106104ff5761010080835404028352916020019161052a565b820191906000526020600020905b81548152906001019060200180831161050d57829003601f168201915b5050505050905090565b600061053f82610de6565b506000908152600460205260409020546001600160a01b031690565b600061056682610adb565b9050806001600160a01b0316836001600160a01b0316036105d35760405162461bcd60e51b815260206004820152602160248201527f4552433732313a20617070726f76616c20746f2063757272656e74206f776e656044820152603960f91b6064820152608401610428565b336001600160a01b03821614806105ef57506105ef8133610365565b6106615760405162461bcd60e51b815260206004820152603d60248201527f4552433732313a20617070726f76652063616c6c6572206973206e6f7420746f60448201527f6b656e206f776e6572206f7220617070726f76656420666f7220616c6c0000006064820152608401610428565b61066b8383610e45565b505050565b600061067b83610adb565b9050336001600160a01b038216146106eb5760405162461bcd60e51b815260206004820152602d60248201527f4552433732313a20596f7520617265206e6f7420746865206f776e6572206f6660448201526c081d1a194818dbdb9d1c9858dd609a1b6064820152608401610428565b506000918252600a6020526040909120805460ff19169115919091179055565b610713610d59565b6000816001600160a01b0316638da5cb5b6040518163ffffffff1660e01b8152600401602060405180830381865afa158015610753573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906107779190611b33565b905061078b6006546001600160a01b031690565b6001600160a01b0316816001600160a01b0316146107fe5760405162461bcd60e51b815260206004820152602a60248201527fe8b4a7e5b881e983a8e7bdb2e88085e4b88de698afe69cace59088e7baa6e79a60448201526984e983a8e7bdb2e8808560b01b6064820152608401610428565b50600880546001600160a01b0319166001600160a01b0392909216919091179055565b610829610d59565b600061083460095490565b9050610844600980546001019055565b61084e8282610eb3565b5050565b61085d335b82610ecd565b6108795760405162461bcd60e51b815260040161042890611b50565b61066b838383610f4c565b61088c610d59565b6008546001600160a01b03166108fa5760405162461bcd60e51b815260206004820152602d60248201527fe8bf98e6b2a1e69c89e5889de5a78be58c96e4baa4e69893e8b4a7e5b881e79a60448201526c0109cb2111cf754dcb3961cb3b609f1b6064820152608401610428565b6008546040516370a0823160e01b81526001600160a01b03868116600483015260009216906370a0823190602401602060405180830381865afa158015610945573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906109699190611b9d565b9050818110156109bb5760405162461bcd60e51b815260206004820152601b60248201527fe8b4ade4b9b0e88085e992b1e58c85e4bd99e9a29de4b88de8b6b300000000006044820152606401610428565b600854604051634758beff60e11b81526001600160a01b03878116600483015286811660248301526044820185905290911690638eb17dfe9060640160006040518083038186803b158015610a0f57600080fd5b505afa158015610a23573d6000803e3d6000fd5b50505050610a318584610d1b565b82846001600160a01b0316866001600160a01b03167fe37f6e1b489a6076a55ed255a7f370750b73eb05fd5001c59dbf1952a3fd33298542604051610a80929190918252602082015260400190565b60405180910390a45050505050565b61066b83838360405180602001604052806000815250610c0c565b610ab333610857565b610acf5760405162461bcd60e51b815260040161042890611b50565b610ad8816110bd565b50565b6000818152600260205260408120546001600160a01b03168061049c5760405162461bcd60e51b8152602060048201526018602482015277115490cdcc8c4e881a5b9d985b1a59081d1bdad95b88125160421b6044820152606401610428565b610b43610d59565b610b586102576006546001600160a01b031690565b565b60006001600160a01b038216610bc45760405162461bcd60e51b815260206004820152602960248201527f4552433732313a2061646472657373207a65726f206973206e6f7420612076616044820152683634b21037bbb732b960b91b6064820152608401610428565b506001600160a01b031660009081526003602052604090205490565b610be8610d59565b610b586000611160565b6060600180546104b190611af9565b61084e3383836111b2565b610c163383610ecd565b610c325760405162461bcd60e51b815260040161042890611b50565b61044a84848484610db3565b6060610c4982610de6565b6000610c53611280565b90506000815111610c735760405180602001604052806000815250610c9e565b80610c7d8461128f565b604051602001610c8e929190611bb6565b6040516020818303038152906040525b9392505050565b610cad610d59565b6001600160a01b038116610d125760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b6064820152608401610428565b610ad881611160565b610d23610d59565b61084e8282604051806020016040528060008152506103b9565b80546001019055565b5490565b6001600160a01b03163b151590565b6006546001600160a01b03163314610b585760405162461bcd60e51b815260206004820181905260248201527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e65726044820152606401610428565b610dbe848484610f4c565b610dca84848484611322565b61044a5760405162461bcd60e51b815260040161042890611be5565b6000818152600260205260409020546001600160a01b0316610ad85760405162461bcd60e51b8152602060048201526018602482015277115490cdcc8c4e881a5b9d985b1a59081d1bdad95b88125160421b6044820152606401610428565b600081815260046020526040902080546001600160a01b0319166001600160a01b0384169081179091558190610e7a82610adb565b6001600160a01b03167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92560405160405180910390a45050565b61084e828260405180602001604052806000815250611423565b600080610ed983610adb565b9050806001600160a01b0316846001600160a01b03161480610f2057506001600160a01b0380821660009081526005602090815260408083209388168352929052205460ff165b80610f445750836001600160a01b0316610f3984610534565b6001600160a01b0316145b949350505050565b826001600160a01b0316610f5f82610adb565b6001600160a01b031614610f855760405162461bcd60e51b815260040161042890611c37565b6001600160a01b038216610fe75760405162461bcd60e51b8152602060048201526024808201527f4552433732313a207472616e7366657220746f20746865207a65726f206164646044820152637265737360e01b6064820152608401610428565b610ff48383836001611456565b826001600160a01b031661100782610adb565b6001600160a01b03161461102d5760405162461bcd60e51b815260040161042890611c37565b600081815260046020908152604080832080546001600160a01b03199081169091556001600160a01b0387811680865260038552838620805460001901905590871680865283862080546001019055868652600290945282852080549092168417909155905184937fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef91a4505050565b60006110c882610adb565b90506110d8816000846001611456565b6110e182610adb565b600083815260046020908152604080832080546001600160a01b03199081169091556001600160a01b0385168085526003845282852080546000190190558785526002909352818420805490911690555192935084927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef908390a45050565b600680546001600160a01b038381166001600160a01b0319831681179093556040519116919082907f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e090600090a35050565b816001600160a01b0316836001600160a01b0316036112135760405162461bcd60e51b815260206004820152601960248201527f4552433732313a20617070726f766520746f2063616c6c6572000000000000006044820152606401610428565b6001600160a01b03838116600081815260056020908152604080832094871680845294825291829020805460ff191686151590811790915591519182527f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c31910160405180910390a3505050565b6060600780546104b190611af9565b6060600061129c836114de565b600101905060008167ffffffffffffffff8111156112bc576112bc611764565b6040519080825280601f01601f1916602001820160405280156112e6576020820181803683370190505b5090508181016020015b600019016f181899199a1a9b1b9c1cb0b131b232b360811b600a86061a8153600a85049450846112f057509392505050565b60006001600160a01b0384163b1561141857604051630a85bd0160e11b81526001600160a01b0385169063150b7a0290611366903390899088908890600401611c7c565b6020604051808303816000875af19250505080156113a1575060408051601f3d908101601f1916820190925261139e91810190611cb9565b60015b6113fe573d8080156113cf576040519150601f19603f3d011682016040523d82523d6000602084013e6113d4565b606091505b5080516000036113f65760405162461bcd60e51b815260040161042890611be5565b805181602001fd5b6001600160e01b031916630a85bd0160e11b149050610f44565b506001949350505050565b61142d83836115b6565b61143a6000848484611322565b61066b5760405162461bcd60e51b815260040161042890611be5565b600181111561044a576001600160a01b0384161561149c576001600160a01b03841660009081526003602052604081208054839290611496908490611cec565b90915550505b6001600160a01b0383161561044a576001600160a01b038316600090815260036020526040812080548392906114d3908490611d03565b909155505050505050565b60008072184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b831061151d5772184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b830492506040015b6d04ee2d6d415b85acef81000000008310611549576d04ee2d6d415b85acef8100000000830492506020015b662386f26fc10000831061156757662386f26fc10000830492506010015b6305f5e100831061157f576305f5e100830492506008015b612710831061159357612710830492506004015b606483106115a5576064830492506002015b600a831061049c5760010192915050565b6001600160a01b03821661160c5760405162461bcd60e51b815260206004820181905260248201527f4552433732313a206d696e7420746f20746865207a65726f20616464726573736044820152606401610428565b6000818152600260205260409020546001600160a01b0316156116715760405162461bcd60e51b815260206004820152601c60248201527f4552433732313a20746f6b656e20616c7265616479206d696e746564000000006044820152606401610428565b61167f600083836001611456565b6000818152600260205260409020546001600160a01b0316156116e45760405162461bcd60e51b815260206004820152601c60248201527f4552433732313a20746f6b656e20616c7265616479206d696e746564000000006044820152606401610428565b6001600160a01b038216600081815260036020908152604080832080546001019055848352600290915280822080546001600160a01b0319168417905551839291907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef908290a45050565b6001600160a01b0381168114610ad857600080fd5b634e487b7160e01b600052604160045260246000fd5b600082601f83011261178b57600080fd5b813567ffffffffffffffff808211156117a6576117a6611764565b604051601f8301601f19908116603f011681019082821181831017156117ce576117ce611764565b816040528381528660208588010111156117e757600080fd5b836020870160208301376000602085830101528094505050505092915050565b60008060006060848603121561181c57600080fd5b83356118278161174f565b925060208401359150604084013567ffffffffffffffff81111561184a57600080fd5b6118568682870161177a565b9150509250925092565b6001600160e01b031981168114610ad857600080fd5b60006020828403121561188857600080fd5b8135610c9e81611860565b60005b838110156118ae578181015183820152602001611896565b8381111561044a5750506000910152565b600081518084526118d7816020860160208601611893565b601f01601f19169290920160200192915050565b602081526000610c9e60208301846118bf565b60006020828403121561191057600080fd5b5035919050565b6000806040838503121561192a57600080fd5b82356119358161174f565b946020939093013593505050565b8035801515811461195357600080fd5b919050565b6000806040838503121561196b57600080fd5b8235915061197b60208401611943565b90509250929050565b60006020828403121561199657600080fd5b8135610c9e8161174f565b6000806000606084860312156119b657600080fd5b83356119c18161174f565b925060208401356119d18161174f565b929592945050506040919091013590565b600080600080608085870312156119f857600080fd5b8435611a038161174f565b93506020850135611a138161174f565b93969395505050506040820135916060013590565b60008060408385031215611a3b57600080fd5b8235611a468161174f565b915061197b60208401611943565b60008060008060808587031215611a6a57600080fd5b8435611a758161174f565b93506020850135611a858161174f565b925060408501359150606085013567ffffffffffffffff811115611aa857600080fd5b611ab48782880161177a565b91505092959194509250565b60008060408385031215611ad357600080fd5b8235611ade8161174f565b91506020830135611aee8161174f565b809150509250929050565b600181811c90821680611b0d57607f821691505b602082108103611b2d57634e487b7160e01b600052602260045260246000fd5b50919050565b600060208284031215611b4557600080fd5b8151610c9e8161174f565b6020808252602d908201527f4552433732313a2063616c6c6572206973206e6f7420746f6b656e206f776e6560408201526c1c881bdc88185c1c1c9bdd9959609a1b606082015260800190565b600060208284031215611baf57600080fd5b5051919050565b60008351611bc8818460208801611893565b835190830190611bdc818360208801611893565b01949350505050565b60208082526032908201527f4552433732313a207472616e7366657220746f206e6f6e20455243373231526560408201527131b2b4bb32b91034b6b83632b6b2b73a32b960711b606082015260800190565b60208082526025908201527f4552433732313a207472616e736665722066726f6d20696e636f72726563742060408201526437bbb732b960d91b606082015260800190565b6001600160a01b0385811682528416602082015260408101839052608060608201819052600090611caf908301846118bf565b9695505050505050565b600060208284031215611ccb57600080fd5b8151610c9e81611860565b634e487b7160e01b600052601160045260246000fd5b600082821015611cfe57611cfe611cd6565b500390565b60008219821115611d1657611d16611cd6565b50019056fea2646970667358221220cecca882c3dd90bff659e53b0fb8b2a6dea3e73579004d060368402ee8ae905064736f6c634300080d00334552433732313a207472616e7366657220746f206e6f6e204552433732315265";

    public static final String FUNC_APPROVALTOADMIN = "approvalToAdmin";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_BUYNFT = "buyNft";

    public static final String FUNC_CURRENCY_ADDRESS = "currency_address";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_GETNOWTIME = "getNowTime";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_NAME = "name";

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

    public static final String FUNC_UPDATECURRENCYADDRESS = "updateCurrencyAddress";

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

    public static final Event SALE_EVENT = new Event("sale", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected MyToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MyToken(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MyToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MyToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
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

    public static List<SaleEventResponse> getSaleEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SALE_EVENT, transactionReceipt);
        ArrayList<SaleEventResponse> responses = new ArrayList<SaleEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SaleEventResponse typedResponse = new SaleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.buy = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.seller = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.time = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SaleEventResponse> saleEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SaleEventResponse>() {
            @Override
            public SaleEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SALE_EVENT, log);
                SaleEventResponse typedResponse = new SaleEventResponse();
                typedResponse.log = log;
                typedResponse.buy = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.seller = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.time = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SaleEventResponse> saleEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SALE_EVENT));
        return saleEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> approvalToAdmin(BigInteger tokenId, Boolean isAllow) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVALTOADMIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.Bool(isAllow)), 
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

    public RemoteFunctionCall<TransactionReceipt> buyNft(String buy, String seller, BigInteger tokenId, BigInteger price) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BUYNFT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, buy), 
                new org.web3j.abi.datatypes.Address(160, seller), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> currency_address() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CURRENCY_ADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getApproved(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPROVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getNowTime() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETNOWTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteFunctionCall<TransactionReceipt> updateCurrencyAddress(String _currency_address) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATECURRENCYADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _currency_address)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static MyToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MyToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MyToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MyToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MyToken load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MyToken(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MyToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MyToken(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<MyToken> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name, String symbol, BigInteger mint_number, String url) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.generated.Uint256(mint_number), 
                new org.web3j.abi.datatypes.Utf8String(url)));
        return deployRemoteCall(MyToken.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<MyToken> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name, String symbol, BigInteger mint_number, String url) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.generated.Uint256(mint_number), 
                new org.web3j.abi.datatypes.Utf8String(url)));
        return deployRemoteCall(MyToken.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<MyToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol, BigInteger mint_number, String url) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.generated.Uint256(mint_number), 
                new org.web3j.abi.datatypes.Utf8String(url)));
        return deployRemoteCall(MyToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<MyToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol, BigInteger mint_number, String url) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.generated.Uint256(mint_number), 
                new org.web3j.abi.datatypes.Utf8String(url)));
        return deployRemoteCall(MyToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
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

    public static class SaleEventResponse extends BaseEventResponse {
        public String buy;

        public String seller;

        public BigInteger tokenId;

        public BigInteger price;

        public BigInteger time;
    }
}
