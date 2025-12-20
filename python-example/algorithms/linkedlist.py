class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

class LinkedList:
    def __init__(self, data):
        self.head = Node(data)

    def is_empty(self):
        return self.head is None

    def get_node(self, index):
        current = self.head
        idx = 0
        while current:
            if idx == index:
                return current
            current = current.next
            idx += 1
        return None

    def add_node(self, index, data):
        if index < 0:
            return False

        new_node = Node(data)
        current = self.head
        if index == 0:
            self.head = new_node
            self.head.next = current
        else:
            prev_node = self.get_node(index-1)
            if prev_node is None:
                return False
            next_node = prev_node.next
            prev_node.next = new_node
            new_node.next = next_node
        return True

    def delete_node(self, index):
        if index < 0:
            return False

        if index == 0:
            first_node = self.head.next
            self.head = first_node
        else:
            prev_node = self.get_node(index-1)
            cur_node = self.get_node(index)
            if prev_node is None or cur_node is None:
                return False
            prev_node.next = cur_node.next
        return True

    def append(self, data):
        new_node = Node(data)
        current = self.head
        while current.next:
            current = current.next
        current.next = new_node

    def pre_append(self, data):
        new_node = Node(data)
        new_node.next = self.head
        self.head = new_node

    def print_all(self):
        list = []
        current = self.head
        while current is not None:
            list.append(current.data)
            current = current.next
        print(list)


def __main__():
    ll = LinkedList(3)
    ll.append(1)
    ll.append(2)
    ll.print_all()
    print('==============')
    node = ll.get_node(0)
    print(node.data)
    print('==============')
    ll.add_node(1, 4)
    ll.print_all()
    ll.add_node(0, 5)
    ll.print_all()
    print('==============')
    ll.delete_node(2)
    ll.print_all()
    ll.delete_node(0)
    ll.print_all()

if __name__ == '__main__':
    __main__()
