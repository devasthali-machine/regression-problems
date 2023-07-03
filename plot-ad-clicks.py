import matplotlib.pyplot as plt
import csv

training_dict = csv.DictReader(open('src/main/resources/ad_click_data_train.csv', 'r'), 
        fieldnames = ['date', 'impressions', 'clicks', 'ctr'], delimiter = ',', quotechar = '"')

dict = csv.DictReader(open('src/main/resources/ad_click_data.csv', 'r'), 
        fieldnames = ['date', 'impressions', 'clicks', 'ctr'], delimiter = ',', quotechar = '"')
i = 1
x = []
y = []
for row in training_dict:
    if row['date']!='date' or row['impressions']!='impressions':
        print(str(i) + ": " + row['impressions'])
        x.append(i)
        y.append(row['impressions'])
        i = i + 1
  
plt.plot(x, y)
  
plt.xlabel('day')
plt.ylabel('ad clicks')
  
plt.title('Click forecasting')
  
plt.show()

