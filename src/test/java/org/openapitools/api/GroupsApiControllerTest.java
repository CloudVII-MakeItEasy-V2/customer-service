package org.openapitools.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.model.Group;
import org.openapitools.model.GroupOrder;
import org.openapitools.service.GroupOrderService;
import org.openapitools.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GroupsApiControllerTest {

    @Mock
    private NativeWebRequest request;

    @Mock
    private GroupService groupService;

    @Mock
    private GroupOrderService groupOrderService;

    @InjectMocks
    private GroupsApiController groupsApiController;

    private UUID groupId;
    private UUID orderId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        groupId = UUID.randomUUID();
        orderId = UUID.randomUUID();
    }

    @Test
    void testGroupsGet() {
        List<Group> groupList = new ArrayList<>();
        groupList.add(new Group());
        when(groupService.getAllGroup()).thenReturn(groupList);

        ResponseEntity<List<Group>> response = groupsApiController.groupsGet();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(groupList, response.getBody());

        when(groupService.getAllGroup()).thenReturn(new ArrayList<>());
        response = groupsApiController.groupsGet();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGroupsGroupIdDelete() {
        when(groupService.getGroupById(groupId)).thenReturn(new Group());

        ResponseEntity<Void> response = groupsApiController.groupsGroupIdDelete(groupId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(groupService).deleteGroupById(groupId);

        when(groupService.getGroupById(groupId)).thenReturn(null);
        response = groupsApiController.groupsGroupIdDelete(groupId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGroupsGroupIdGet() {
        Group group = new Group();
        when(groupService.getGroupById(groupId)).thenReturn(group);

        ResponseEntity<Group> response = groupsApiController.groupsGroupIdGet(groupId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(group, response.getBody());

        when(groupService.getGroupById(groupId)).thenReturn(null);
        response = groupsApiController.groupsGroupIdGet(groupId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGroupsGroupIdPut() {
        Group updatedGroup = new Group();
        when(groupService.getGroupById(groupId)).thenReturn(new Group());

        ResponseEntity<Void> response = groupsApiController.groupsGroupIdPut(groupId, updatedGroup);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(groupService).updateGroupById(groupId, updatedGroup);

        when(groupService.getGroupById(groupId)).thenReturn(null);
        response = groupsApiController.groupsGroupIdPut(groupId, updatedGroup);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGroupsPost() {
        Group newGroup = new Group();

        ResponseEntity<Void> response = groupsApiController.groupsPost(newGroup);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(groupService).addGroup(newGroup);
    }

    @Test
    void testGroupsGroupIdOrdersOrderIdDelete() {
        when(groupService.deleteGroupOrder(groupId, orderId)).thenReturn(true);

        ResponseEntity<Void> response = groupsApiController.groupsGroupIdOrdersOrderIdDelete(groupId, orderId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        when(groupService.deleteGroupOrder(groupId, orderId)).thenReturn(false);
        response = groupsApiController.groupsGroupIdOrdersOrderIdDelete(groupId, orderId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGroupsGroupIdOrdersGet() {
        List<GroupOrder> orderList = new ArrayList<>();
        orderList.add(new GroupOrder());
        when(groupService.getGroupOrdersByGroupId(groupId)).thenReturn(orderList);

        ResponseEntity<List<GroupOrder>> response = groupsApiController.groupsGroupIdOrdersGet(groupId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderList, response.getBody());

        when(groupService.getGroupOrdersByGroupId(groupId)).thenReturn(new ArrayList<>());
        response = groupsApiController.groupsGroupIdOrdersGet(groupId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGroupsGroupIdOrdersOrderIdGet() {
        when(groupService.hasGroupOrder(groupId, orderId)).thenReturn(true);
        GroupOrder order = new GroupOrder();
        when(groupOrderService.getGroupOrderById(orderId)).thenReturn(order);

        ResponseEntity<GroupOrder> response = groupsApiController.groupsGroupIdOrdersOrderIdGet(groupId, orderId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order, response.getBody());

        when(groupService.hasGroupOrder(groupId, orderId)).thenReturn(false);
        response = groupsApiController.groupsGroupIdOrdersOrderIdGet(groupId, orderId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGroupsGroupIdOrdersOrderIdPut() {
        GroupOrder updatedOrder = new GroupOrder();
        when(groupService.hasGroupOrder(groupId, orderId)).thenReturn(true);
        when(groupOrderService.updateGroupOrder(orderId, updatedOrder)).thenReturn(true);

        ResponseEntity<Void> response = groupsApiController.groupsGroupIdOrdersOrderIdPut(groupId, orderId, updatedOrder);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        when(groupOrderService.updateGroupOrder(orderId, updatedOrder)).thenReturn(false);
        response = groupsApiController.groupsGroupIdOrdersOrderIdPut(groupId, orderId, updatedOrder);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        when(groupService.hasGroupOrder(groupId, orderId)).thenReturn(false);
        response = groupsApiController.groupsGroupIdOrdersOrderIdPut(groupId, orderId, updatedOrder);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testGroupsGroupIdOrdersPost() {
        GroupOrder newOrder = new GroupOrder();
        when(groupService.addGroupOrder(groupId, newOrder)).thenReturn(true);

        ResponseEntity<Void> response = groupsApiController.groupsGroupIdOrdersPost(groupId, newOrder);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        when(groupService.addGroupOrder(groupId, newOrder)).thenReturn(false);
        response = groupsApiController.groupsGroupIdOrdersPost(groupId, newOrder);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
