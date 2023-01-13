package com.example.web3study.smartContract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.DynamicStruct;
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
public class MinimalForwarder extends Contract {
    public static final String BINARY = "0x61014060405234801561001157600080fd5b50604080518082018252601081526f26b4b734b6b0b62337b93bb0b93232b960811b602080830191825283518085019094526005845264302e302e3160d81b908401528151902060e08190527fae209a0b48f21c054280f2455d32cf309387644879d9acbd8ffc1991638118856101008190524660a0529192917f8b73c3c69bb8fe3d512ecc4cf759cc79239f7b179b0ffacaa9a75d522b39400f6100fb8184846040805160208101859052908101839052606081018290524660808201523060a082015260009060c0016040516020818303038152906040528051906020012090509392505050565b6080523060c052610120525061011092505050565b60805160a05160c05160e0516101005161012051610a4761015f60003960006104ec0152600061053b015260006105160152600061046f01526000610499015260006104c30152610a476000f3fe6080604052600436106100345760003560e01c80632d0335ab1461003957806347153f8214610082578063bf5d3bdb146100a3575b600080fd5b34801561004557600080fd5b5061006f6100543660046107df565b6001600160a01b031660009081526020819052604090205490565b6040519081526020015b60405180910390f35b61009561009036600461080f565b6100d3565b6040516100799291906108de565b3480156100af57600080fd5b506100c36100be36600461080f565b61026c565b6040519015158152602001610079565b600060606100e285858561026c565b61014e5760405162461bcd60e51b815260206004820152603260248201527f4d696e696d616c466f727761726465723a207369676e617475726520646f6573604482015271081b9bdd081b585d18da081c995c5d595cdd60721b60648201526084015b60405180910390fd5b61015d6080860135600161091a565b60008061016d60208901896107df565b6001600160a01b03166001600160a01b03168152602001908152602001600020819055506000808660200160208101906101a791906107df565b6001600160a01b0316606088013560408901356101c760a08b018b610940565b6101d460208d018d6107df565b6040516020016101e693929190610987565b60408051601f1981840301815290829052610200916109ad565b600060405180830381858888f193505050503d806000811461023e576040519150601f19603f3d011682016040523d82523d6000602084013e610243565b606091505b509092509050610258603f60608901356109c9565b5a1161026057fe5b90969095509350505050565b60008061037f84848080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525061037992507fdd8f4b70b0f4393e889bd39128a30628a78b61816a9eb8199759e7a349657e4891506102dc905060208a018a6107df565b6102ec60408b0160208c016107df565b60408b013560608c013560808d013561030860a08f018f610940565b6040516103169291906109eb565b6040805191829003822060208301989098526001600160a01b0396871690820152949093166060850152608084019190915260a083015260c082015260e081019190915261010001604051602081830303815290604052805190602001206103ea565b9061043e565b9050608085013560008061039660208901896107df565b6001600160a01b03166001600160a01b03168152602001908152602001600020541480156103e157506103cc60208601866107df565b6001600160a01b0316816001600160a01b0316145b95945050505050565b60006104386103f7610462565b8360405161190160f01b6020820152602281018390526042810182905260009060620160405160208183030381529060405280519060200120905092915050565b92915050565b600080600061044d8585610589565b9150915061045a816105ce565b509392505050565b6000306001600160a01b037f0000000000000000000000000000000000000000000000000000000000000000161480156104bb57507f000000000000000000000000000000000000000000000000000000000000000046145b156104e557507f000000000000000000000000000000000000000000000000000000000000000090565b50604080517f00000000000000000000000000000000000000000000000000000000000000006020808301919091527f0000000000000000000000000000000000000000000000000000000000000000828401527f000000000000000000000000000000000000000000000000000000000000000060608301524660808301523060a0808401919091528351808403909101815260c0909201909252805191012090565b60008082516041036105bf5760208301516040840151606085015160001a6105b38782858561071b565b945094505050506105c7565b506000905060025b9250929050565b60008160048111156105e2576105e26109fb565b036105ea5750565b60018160048111156105fe576105fe6109fb565b0361064b5760405162461bcd60e51b815260206004820152601860248201527f45434453413a20696e76616c6964207369676e617475726500000000000000006044820152606401610145565b600281600481111561065f5761065f6109fb565b036106ac5760405162461bcd60e51b815260206004820152601f60248201527f45434453413a20696e76616c6964207369676e6174757265206c656e677468006044820152606401610145565b60038160048111156106c0576106c06109fb565b036107185760405162461bcd60e51b815260206004820152602260248201527f45434453413a20696e76616c6964207369676e6174757265202773272076616c604482015261756560f01b6064820152608401610145565b50565b6000807f7fffffffffffffffffffffffffffffff5d576e7357a4501ddfe92f46681b20a083111561075257506000905060036107d6565b6040805160008082526020820180845289905260ff881692820192909252606081018690526080810185905260019060a0016020604051602081039080840390855afa1580156107a6573d6000803e3d6000fd5b5050604051601f1901519150506001600160a01b0381166107cf576000600192509250506107d6565b9150600090505b94509492505050565b6000602082840312156107f157600080fd5b81356001600160a01b038116811461080857600080fd5b9392505050565b60008060006040848603121561082457600080fd5b833567ffffffffffffffff8082111561083c57600080fd5b9085019060c0828803121561085057600080fd5b9093506020850135908082111561086657600080fd5b818601915086601f83011261087a57600080fd5b81358181111561088957600080fd5b87602082850101111561089b57600080fd5b6020830194508093505050509250925092565b60005b838110156108c95781810151838201526020016108b1565b838111156108d8576000848401525b50505050565b821515815260406020820152600082518060408401526109058160608501602087016108ae565b601f01601f1916919091016060019392505050565b6000821982111561093b57634e487b7160e01b600052601160045260246000fd5b500190565b6000808335601e1984360301811261095757600080fd5b83018035915067ffffffffffffffff82111561097257600080fd5b6020019150368190038213156105c757600080fd5b8284823760609190911b6bffffffffffffffffffffffff19169101908152601401919050565b600082516109bf8184602087016108ae565b9190910192915050565b6000826109e657634e487b7160e01b600052601260045260246000fd5b500490565b8183823760009101908152919050565b634e487b7160e01b600052602160045260246000fdfea2646970667358221220786c740e83444903f12c9c03112d1316c5624f903b5fba1d292481138dd460a564736f6c634300080d0033";

    public static final String FUNC_EXECUTE = "execute";

    public static final String FUNC_GETNONCE = "getNonce";

    public static final String FUNC_VERIFY = "verify";

    @Deprecated
    protected MinimalForwarder(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MinimalForwarder(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MinimalForwarder(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MinimalForwarder(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> execute(ForwardRequest req, byte[] signature, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_EXECUTE, 
                Arrays.<Type>asList(req, 
                new org.web3j.abi.datatypes.DynamicBytes(signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<BigInteger> getNonce(String from) {
        final Function function = new Function(FUNC_GETNONCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> verify(ForwardRequest req, byte[] signature) {
        final Function function = new Function(FUNC_VERIFY, 
                Arrays.<Type>asList(req, 
                new org.web3j.abi.datatypes.DynamicBytes(signature)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static MinimalForwarder load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MinimalForwarder(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MinimalForwarder load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MinimalForwarder(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MinimalForwarder load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MinimalForwarder(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MinimalForwarder load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MinimalForwarder(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<MinimalForwarder> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MinimalForwarder.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<MinimalForwarder> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MinimalForwarder.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<MinimalForwarder> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MinimalForwarder.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<MinimalForwarder> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MinimalForwarder.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ForwardRequest extends DynamicStruct {
        public String from;

        public String to;

        public BigInteger value;

        public BigInteger gas;

        public BigInteger nonce;

        public byte[] data;

        public ForwardRequest(String from, String to, BigInteger value, BigInteger gas, BigInteger nonce, byte[] data) {
            super(new org.web3j.abi.datatypes.Address(160, from), 
                    new org.web3j.abi.datatypes.Address(160, to), 
                    new org.web3j.abi.datatypes.generated.Uint256(value), 
                    new org.web3j.abi.datatypes.generated.Uint256(gas), 
                    new org.web3j.abi.datatypes.generated.Uint256(nonce), 
                    new org.web3j.abi.datatypes.DynamicBytes(data));
            this.from = from;
            this.to = to;
            this.value = value;
            this.gas = gas;
            this.nonce = nonce;
            this.data = data;
        }

        public ForwardRequest(Address from, Address to, Uint256 value, Uint256 gas, Uint256 nonce, DynamicBytes data) {
            super(from, to, value, gas, nonce, data);
            this.from = from.getValue();
            this.to = to.getValue();
            this.value = value.getValue();
            this.gas = gas.getValue();
            this.nonce = nonce.getValue();
            this.data = data.getValue();
        }
    }
}
