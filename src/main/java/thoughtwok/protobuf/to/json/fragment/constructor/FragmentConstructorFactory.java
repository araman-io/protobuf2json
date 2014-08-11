package thoughtwok.protobuf.to.json.fragment.constructor;

/**
 * An interface to get hold of <code>FragmentConstructor</code>
 * @author araman
 */
public interface FragmentConstructorFactory {
    
    /**
     * return an instance of fragment constructor for a type
     * @param type
     * @return
     */
    public FragmentConstructor getFragmentConstructorForType(String type);

}
