class Node:
    def __init__(self, data):
        self.data = data
        self.next = None


class LinkedList:
    def __init__(self, value):
        self.head = Node(value)

    def append(self, value):
        cur = self.head
        while cur.next is not None:
            cur = cur.next
        cur.next = Node(value)


def get_linked_list_sum(linked_list_1, linked_list_2):
    dummy = Node(0)
    carry = 0
    cur = dummy
    l1 = linked_list_1.head
    l2 = linked_list_2.head
    while l1 or l2 or carry:
        val1 = l1.data if l1 else 0
        val2 = l2.data if l2 else 0

        new_val = val1 + val2 + carry
        carry = new_val // 10
        cur.next = Node(new_val % 10)
        cur = cur.next
        l1 = l1.next if l1 else None
        l2 = l2.next if l2 else None

    return dummy.next


def print_all(list):
    cur = list
    while cur:
        print(cur.data, end=' ')
        cur = cur.next

def __main__():
    linked_list_1 = LinkedList(6)
    linked_list_1.append(7)
    linked_list_1.append(8)

    linked_list_2 = LinkedList(3)
    linked_list_2.append(5)
    linked_list_2.append(4)

    sum_list = get_linked_list_sum(linked_list_1, linked_list_2)
    print_all(sum_list)
    print()

    linked_list_1 = LinkedList(9)
    linked_list_2 = LinkedList(9)

    sum_list = get_linked_list_sum(linked_list_1, linked_list_2)
    print_all(sum_list)
    print()

    linked_list_1 = LinkedList(9)
    linked_list_1.append(2)
    linked_list_2 = LinkedList(9)
    linked_list_2.append(1)
    linked_list_2.append(3)

    sum_list = get_linked_list_sum(linked_list_1, linked_list_2)
    print_all(sum_list)

if __name__ == '__main__':
    __main__()