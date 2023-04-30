te = () # emtpy tuple
t = (2, "mit", 3)
print(t[0])
print(len(t)) # length of tuple t

n = ("Nil","Kim")

def get_data(aTuple):
    nums = ()
    words = ()
    for t in aTuple:
        nums = nums + (t[0],)
        if t[1] not in words:
            words = words + (t[1],)
    min_n = min(nums)
    max_n = max(nums)
    unique_words = len(words)
    return (min_n, max_n, unique_words)

print(get_data(n))

tswift = ((2014,"Katy"),(2014,"Harry"),(2012,"Jake"),(2010,"Taylor"),(2008,"Joe"))
(min_year, max_year, num_people) = get_data(tswift)
print("From",min_year,"to",max_year,"talor swift wrote songs about",num_people)


str = "123<123"
print(str.split('<'))

L = [9,6,0,3]
L.sort()
print(L)
L.reverse()
print(L)