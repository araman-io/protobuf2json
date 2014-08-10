package thoughtwok.protobuf.to.json.fragment.constructor;

public enum FragmentConstructorEnum {
    
    BOOLEAN(BooleanFragmentConstuctor.INSTANCE), 
    BYTE_STRING(SimpleFragmentConstructor.INSTANCE), 
    DOUBLE(SimpleFragmentConstructor.INSTANCE), 
    ENUM(EnumFragmentConstructor.INSTANCE), 
    FLOAT(SimpleFragmentConstructor.INSTANCE), 
    INT(IntFragmentConstructor.INSTANCE), 
    LONG(SimpleFragmentConstructor.INSTANCE), 
    MESSAGE(MessageFragmentConstructor.INSTANCE), 
    STRING(SimpleFragmentConstructor.INSTANCE);

    FragmentConstructor c;

    private FragmentConstructorEnum(FragmentConstructor constructor) {
        this.c = constructor;
    }
    
    public FragmentConstructor getFragmentConstructor() {
        return this.c;
    }

}
