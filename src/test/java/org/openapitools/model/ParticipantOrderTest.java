package org.openapitools.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ParticipantOrderTest {

    private ParticipantOrder participantOrder;
    private UUID sampleParticipantOrderID;
    private UUID sampleMenuItemID;
    private Map<UUID, Integer> sampleMenuItemIDs;
    private String sampleComments;
    private Participant sampleParticipant;

    @BeforeEach
    public void setUp() {
        sampleParticipantOrderID = UUID.randomUUID();
        sampleMenuItemID = UUID.randomUUID();
        sampleMenuItemIDs = new HashMap<>();
        sampleMenuItemIDs.put(sampleMenuItemID, 2);
        sampleComments = "Sample comments";
        sampleParticipant = new Participant();

        participantOrder = new ParticipantOrder()
                .participantOrderID(sampleParticipantOrderID)
                .menuItemIDs(sampleMenuItemIDs)
                .comments(sampleComments);
        participantOrder.setParticipant(sampleParticipant);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(sampleParticipantOrderID, participantOrder.getParticipantOrderID());
        assertEquals(sampleMenuItemIDs, participantOrder.getMenuItemIDs());
        assertEquals(sampleComments, participantOrder.getComments());
        assertEquals(sampleParticipant, participantOrder.getParticipant());
    }

    @Test
    public void testPutMenuItemIDsItem() {
        UUID newMenuItemID = UUID.randomUUID();
        participantOrder.putMenuItemIDsItem(newMenuItemID, 3);

        assertThat(participantOrder.getMenuItemIDs()).containsEntry(newMenuItemID, 3);
    }

    @Test
    public void testEquals() {
        ParticipantOrder anotherParticipantOrder = new ParticipantOrder()
                .participantOrderID(sampleParticipantOrderID)
                .menuItemIDs(sampleMenuItemIDs)
                .comments(sampleComments);
        anotherParticipantOrder.setParticipant(sampleParticipant);

        assertThat(participantOrder).isEqualTo(anotherParticipantOrder);
    }

    @Test
    public void testNotEquals() {
        ParticipantOrder differentParticipantOrder = new ParticipantOrder()
                .participantOrderID(UUID.randomUUID())
                .menuItemIDs(new HashMap<>())
                .comments("Different comments");
        differentParticipantOrder.setParticipant(new Participant());

        assertNotEquals(participantOrder, differentParticipantOrder);
    }

    @Test
    public void testHashCode() {
        ParticipantOrder anotherParticipantOrder = new ParticipantOrder()
                .participantOrderID(sampleParticipantOrderID)
                .menuItemIDs(sampleMenuItemIDs)
                .comments(sampleComments);
        anotherParticipantOrder.setParticipant(sampleParticipant);

        assertEquals(participantOrder.hashCode(), anotherParticipantOrder.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "class ParticipantOrder {\n" +
                "    participantOrderID: " + sampleParticipantOrderID + "\n" +
                "    menuItemIDs: " + sampleMenuItemIDs + "\n" +
                "    comments: " + sampleComments + "\n" +
                "    participant: " + sampleParticipant + "\n" +
                "}";

    }

    @Test
    public void testParticipantOrderChainedMethods() {
        ParticipantOrder chainedParticipantOrder = new ParticipantOrder()
                .participantOrderID(sampleParticipantOrderID)
                .menuItemIDs(sampleMenuItemIDs)
                .comments(sampleComments);

        assertEquals(sampleParticipantOrderID, chainedParticipantOrder.getParticipantOrderID());
        assertEquals(sampleMenuItemIDs, chainedParticipantOrder.getMenuItemIDs());
        assertEquals(sampleComments, chainedParticipantOrder.getComments());
    }
}
