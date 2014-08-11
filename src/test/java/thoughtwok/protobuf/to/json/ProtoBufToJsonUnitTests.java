package thoughtwok.protobuf.to.json;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import thoughtwok.protobuf.to.json.fragment.constructor.base.BooleanFragmentConstructorTest;
import thoughtwok.protobuf.to.json.fragment.constructor.base.IntFragmentConstructorTest;
import thoughtwok.protobuf.to.json.fragment.constructor.synsugar.FragmentConstructorFactoryWithSyntaticSugarTest;
import thoughtwok.protobuf.to.json.fragment.constructor.synsugar.MapFragmentConstructorTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({BooleanFragmentConstructorTest.class, IntFragmentConstructorTest.class,
        FragmentConstructorFactoryWithSyntaticSugarTest.class, MapFragmentConstructorTest.class})
public class ProtoBufToJsonUnitTests {

}
