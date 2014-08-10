package thoughtwok.protobuf.to.json;

import static org.junit.Assert.*;

import org.junit.Test;

import thoughtwok.protobuf.to.json.test.SamplesProtos;
import thoughtwok.protobuf.to.json.test.SamplesProtos.Person;
import thoughtwok.protobuf.to.json.test.SamplesProtos.Person.PhoneNumber;
import thoughtwok.protobuf.to.json.test.SamplesProtos.Person.PhoneType;

import com.google.protobuf.Message;

public class ProtoBufToJsonTest {

    @Test
    public void shouldReturnInstance() {
        Object o = ProtoBufToJson.INSTANCE;
        assertTrue(o instanceof ProtoBufToJson);
    }

    @Test
    public void shouldReturnEmptyJsonIfMessageIsNull() {
        String s = ProtoBufToJson.INSTANCE.print(null);
        assertNull(s);
    }

    @Test
    public void shouldPrintSimpleKeyValue() {
        Person p = SamplesProtos.Person.newBuilder().setName("foobar").setId(1).build();
        String s = ProtoBufToJson.INSTANCE.print(p);
        System.out.println(s);
    }

    @Test
    public void shouldPrintSimpleMessage() {
        Message m =
                SamplesProtos.SimpleMessage.newBuilder().setId(1).setTitle("my post")
                        .setPosted(Boolean.TRUE.booleanValue()).setRate(3.1415f).setPi(Math.PI).build();
        String s = ProtoBufToJson.INSTANCE.print(m);
        System.out.println(s);
    }
    
    @Test
    public void shouldPrintMessageWithRepeatedFields() {
        Message m =
                SamplesProtos.SimpleMessageWithRepeatedFields.newBuilder().setId(1).setTitle("my post")
                        .setPosted(Boolean.TRUE.booleanValue()).setRate(3.1415f).setPi(Math.PI)
                        .addTags("foo").addTags("bar").addCommentId(1212).addCommentId(13432452).addCommentId(56).build();
        String s = ProtoBufToJson.INSTANCE.print(m);
        System.out.println(s);
    }

    @Test
    public void shouldPrintMessageWithinMessage() {
        Message m =
                SamplesProtos.MessageWithAnotherMessage.newBuilder().setId(1).setTitle("my post")
                        .setPosted(Boolean.TRUE.booleanValue()).setRate(3.1415f).setPi(Math.PI)
                        .addTags("foo").addTags("bar").addCommentId(1212).addCommentId(13432452).addCommentId(56)
                        .setPerson(Person.newBuilder().setId(89797).setName("foo bar inc").setEmail("foo@bar.com").build()).build();
        String s = ProtoBufToJson.INSTANCE.print(m);
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
        String s = ProtoBufToJson.INSTANCE.print(m);
        System.out.println(s);
    }
    
    @Test
    public void shouldPrintMessageWithRepeatedMessages2() {
        Message m =
                SamplesProtos.Person.newBuilder().setId(1).setName("foo bar")
                        .setEmail("foo@bar.com").addPhone(PhoneNumber.newBuilder().setNumber("1234567890").setType(PhoneType.HOME).build())
                        .addPhone(PhoneNumber.newBuilder().setNumber("0998e7r9q8e78r6").setType(PhoneType.MOBILE).build())
                        .build();
        String s = ProtoBufToJson.INSTANCE.print(m);
        System.out.println(s);
    }
}
