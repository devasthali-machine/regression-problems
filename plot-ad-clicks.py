import matplotlib.pyplot as plt
import csv
import numpy as np

training_dict = csv.DictReader(open('src/main/resources/ad_click_data_train.csv', 'r'),
        fieldnames = ['date', 'impressions', 'clicks', 'ctr'], delimiter = ',', quotechar = '"')

testing_dict = csv.DictReader(open('src/main/resources/ad_click_data.csv', 'r'),
        fieldnames = ['date', 'impressions', 'clicks', 'ctr'], delimiter = ',', quotechar = '"')
i = 1
train_x = []
train_y = []
test_y = []

for row in training_dict:
    if row['date']!='date' or row['impressions']!='impressions':
        print(str(i) + ": " + row['impressions'])
        train_x.append(i)
        train_y.append(int(row['impressions']))
        i = i + 1

for row in testing_dict:
  if row['date']!='date' or row['impressions']!='impressions':
    print(str(i) + ": " + row['impressions'])
    train_x.append(i)
    test_y.append(int(row['impressions']))
    i = i + 1

total = len(train_y) + len(test_y)

ii = len(train_y)

train_a = np.empty(total - len(train_y))
train_a.fill(0)

test_a = np.empty(total - len(test_y))
test_a.fill(0)

a = train_y + list(train_a)
b = list(test_a) + test_y

plt.plot([],[],color='c', label='Training Impressions', linewidth=5)
plt.plot([],[],color='y', label='Forecasted Impressions', linewidth=5)
plt.stackplot(train_x, a, b, colors=['c', 'y'])

plt.xlabel('day')
plt.ylabel('ad clicks')

plt.title('Click forecasting')

plt.show()

