package thoughtwok.protobuf.to.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import thoughtwok.protobuf.to.json.fragment.constructor.synsugar.FragmentConstructorFactoryWithSyntaticSugar;
import thoughtwok.protobuf.to.json.test.SamplesProtos;
import thoughtwok.protobuf.to.json.test.SamplesProtos.KeyValue;
import thoughtwok.protobuf.to.json.test.SamplesProtos.MessageWithBoolField;
import thoughtwok.protobuf.to.json.test.SamplesProtos.MessageWithJustOneKeyValue;
import thoughtwok.protobuf.to.json.test.SamplesProtos.MessageWithKeyValue;
import thoughtwok.protobuf.to.json.test.SamplesProtos.Person;
import thoughtwok.protobuf.to.json.test.SamplesProtos.Person.PhoneNumber;
import thoughtwok.protobuf.to.json.test.SamplesProtos.Person.PhoneType;

import com.google.protobuf.Message;

public class ProtoBufToJsonIntegrationTest {

    @Test
    public void shouldReturnInstance() {
        Object o = ProtoBufToJson.DEFAULT_INSTANCE;
        assertTrue(o instanceof ProtoBufToJson);
    }

    @Test
    public void shouldReturnEmptyJsonIfMessageIsNull() {
        try {
            String s = ProtoBufToJson.DEFAULT_INSTANCE.print(null);
            fail("should have thrown a null pointer exception");
        } catch(NullPointerException npe) {
            //expected
        }
        
    }

    @Test
    public void shouldPrintSimpleKeyValue() {
        Person p = SamplesProtos.Person.newBuilder().setName("foobar").setId(1).build();
        String s = ProtoBufToJson.DEFAULT_INSTANCE.print(p);
        System.out.println(s);
    }

    @Test
    public void shouldPrintSimpleMessage() {
        Message m =
                SamplesProtos.SimpleMessage.newBuilder().setId(1).setTitle("my post")
                        .setPosted(Boolean.TRUE.booleanValue()).setRate(3.1415f).setPi(Math.PI).build();
        String s = ProtoBufToJson.DEFAULT_INSTANCE.print(m);
        System.out.println(s);
    }
    
    @Test
    public void shouldPrintMessageWithRepeatedFields() {
        Message m =
                SamplesProtos.SimpleMessageWithRepeatedFields.newBuilder().setId(1).setTitle("my post")
                        .setPosted(Boolean.TRUE.booleanValue()).setRate(3.1415f).setPi(Math.PI)
                        .addTags("foo").addTags("bar").addCommentId(1212).addCommentId(13432452).addCommentId(56).build();
        String s = ProtoBufToJson.DEFAULT_INSTANCE.print(m);
        System.out.println(s);
    }

    @Test
    public void shouldPrintMessageWithinMessage() {
        Message m =
                SamplesProtos.MessageWithAnotherMessage.newBuilder().setId(1).setTitle("my post")
                        .setPosted(Boolean.TRUE.booleanValue()).setRate(3.1415f).setPi(Math.PI)
                        .addTags("foo").addTags("bar").addCommentId(1212).addCommentId(13432452).addCommentId(56)
                        .setPerson(Person.newBuilder().setId(89797).setName("foo bar inc").setEmail("foo@bar.com").build()).build();
        String s = ProtoBufToJson.DEFAULT_INSTANCE.print(m);
        System.out.println(s);
    }
    
    @Test
    public void shouldPrintMessageWithRepeatedMessages() {
        Message m =
                SamplesProtos.MessageWithRepeatedMessage.newBuilder().setId(1).setTitle("my post")
                        .setPosted(Boolean.TRUE.booleanValue()).addTags("foo").addTags("bar").addCommentId(1212).addCommentId(13432452).addCommentId(56)
                        .addPerson(Person.newBuilder().setId(89797).setName("foo bar inc").setEmail("foo@bar.com").build())
                        .addPerson(Person.newBuilder().setId(89797).setName("bar foo inc").setEmail("bar@foo.com").build())
                        .build();
        String s = ProtoBufToJson.DEFAULT_INSTANCE.print(m);
        System.out.println(s);
    }
    
    @Test
    public void shouldPrintMessageWithRepeatedMessages2() {
        Message m =
                SamplesProtos.Person.newBuilder().setId(1).setName("foo bar")
                        .setEmail("foo@bar.com").addPhone(PhoneNumber.newBuilder().setNumber("1234567890").setType(PhoneType.HOME).build())
                        .addPhone(PhoneNumber.newBuilder().setNumber("0998e7r9q8e78r6").setType(PhoneType.MOBILE).build())
                        .build();
        String s = ProtoBufToJson.DEFAULT_INSTANCE.print(m);
        System.out.println(s);
    }
    
    @Test
    public void shouldOutputMessageWithBooleanFields() {
        Message m = MessageWithBoolField.newBuilder().setAlive(true).addPresent(true).addPresent(true).addPresent(false).build();
        String s = ProtoBufToJson.DEFAULT_INSTANCE.print(m);
        
        assertEquals("{\"alive\":true,\"present\":[true,true,false]}", s);
    }
    
    @Test
    public void testIntegration() {
        Message aRecordWith1Keys =
                MessageWithJustOneKeyValue.newBuilder()
                        .setRecord(KeyValue.newBuilder().setKey("foo").setValue("bar").build()).build();

        Message aRecordWithRepeatedKeyValue =
                MessageWithKeyValue.newBuilder().addRecord(KeyValue.newBuilder().setKey("foo").setValue("bar").build())
                        .addRecord(KeyValue.newBuilder().setKey("foo1").setValue("bar1").build()).build();

        ProtoBufToJson bufToJson = new ProtoBufToJson(new FragmentConstructorFactoryWithSyntaticSugar());

        assertEquals("{\"record\":{\"foo\":\"bar\"}}", bufToJson.print(aRecordWith1Keys));
        assertEquals("{\"record\":{\"foo\":\"bar\",\"foo1\":\"bar1\"}}", bufToJson.print(aRecordWithRepeatedKeyValue));
    }

}
