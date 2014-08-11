package thoughtwok.protobuf.to.json.fragment.constructor.synsugar;

import com.google.protobuf.Descriptors.FieldDescriptor;

import thoughtwok.protobuf.to.json.fragment.constructor.FragmentConstructor;
import thoughtwok.protobuf.to.json.fragment.constructor.base.DefaultFragmentConstructorFactory;

/**
 * A <code>FragmentConstructorFactory</code> with some support for syntatic sugar. protocol buffers doesnt inherently
 * support Map data structure. When you define a field in the definition to look like
 * 
 * <pre>
 *  message MessageWithKeyValue {
 *      repeated KeyValue record = 2;
 *  }
 * 
 *  message KeyValue {
 *      optional string key = 1;
 *      optional string value = 2;
 *  }
 * </pre>
 * 
 * The <code>DefaultFragmentConstructorFactory</code> will render it as follows
 * 
 * <pre>
 * {
 *  "record": [{
 *      "key": "foo",
 *      "value": "bar"
 *  }, {
 *      "key": "foo1",
 *      "value": "bar1"
 *  }]
 *  }
 * </pre>
 * 
 * However if you want the same <code>Message</code> to be rendered as follows
 * 
 * <pre>
 * {
 *  "record": {
 *      "foo": "bar",
 *      "foo1": "bar1"
 *  }
 * }
 * </pre>
 * 
 * Use the <code>FragmentConstructorFactoryWithSyntaticSugar</code>. This factory treats messages with name
 * <code>KeyValue</code> uniquely. The key and value are paired up and printed as a JSON object using the <code>MapFragmentConstructor</code>
 */
public class FragmentConstructorFactoryWithSyntaticSugar extends DefaultFragmentConstructorFactory {

    @Override
    public FragmentConstructor getFragmentConstructorForType(FieldDescriptor descriptor) {

        FragmentConstructor resolvedConstructor = null;

        // only if it is a message and has the name name KeyValue
        // then return a different fragment constructor
        if (descriptor.getJavaType().name().equals("MESSAGE")
                && descriptor.getMessageType().getName().equals("KeyValue")) {
            resolvedConstructor = MapFragmentConstructor.INSTANCE;
        } else {
            resolvedConstructor = super.getFragmentConstructorForType(descriptor);
        }

        return resolvedConstructor;
    }

}
