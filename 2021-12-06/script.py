f = open("testInput.txt", "r");
values = f.readline();
valuesList = values.split(",");

for i in range(0, len(valuesList)):
    valuesList[i] = int(valuesList[i]);

for day in range(256):
    newAdditions = 0;
    for x in range(0, len(valuesList)):
        if (valuesList[x] == 0):
            newAdditions = newAdditions + 1;
            valuesList[x] = 6;
        else:
            valuesList[x] = valuesList[x] - 1;

    for x in range(0, newAdditions):
        valuesList.append(8)

    newAdditions = 0;
    print(str(day) + " " + str(len(valuesList)));

print(len(valuesList));