import matplotlib.pyplot as plt
import csv
import numpy as np

training_dict = csv.DictReader(open('src/main/resources/ad_click_data_train.csv', 'r'),
        fieldnames = ['date', 'impressions', 'clicks', 'ctr'], delimiter = ',', quotechar = '"')

testing_dict = csv.DictReader(open('src/main/resources/ad_click_data_test.csv', 'r'),
        fieldnames = ['date', 'impressions', 'clicks', 'ctr'], delimiter = ',', quotechar = '"')

prediction_dict = csv.DictReader(open('src/main/resources/ad_click_data_predictions.csv', 'r'),
                              fieldnames = ['date', 'impressions'], delimiter = ',', quotechar = '"')

i = 1
train_x = []
train_y = []
test_y = []
prediction_y = []

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

for row in prediction_dict:
  if row['date']!='date' or row['impressions']!='impressions':
    prediction_y.append(int(row['impressions']))

total = len(train_y) + len(test_y)

ii = len(train_y)

train_a = np.empty(total - len(train_y))
train_a.fill(0)

test_a = np.empty(total - len(test_y))
test_a.fill(0)

a = train_y + list(train_a)
b = list(test_a) + test_y
c = list(test_a) + prediction_y

plt.plot([],[],color='c', label='Training Impressions', linewidth=5)
plt.plot([],[],color='y', label='Actual Impressions', linewidth=5)
plt.plot([],[],color='m', label='Forecasted Impressions', linewidth=5)

plt.stackplot(train_x, a, b, c, colors=['c', 'y', 'm'])

plt.xlabel('day')
plt.ylabel('ad clicks')

plt.title('Click forecasting')
plt.legend()
plt.show()

