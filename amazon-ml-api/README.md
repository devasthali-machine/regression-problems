
ML to Predict Responses to a Marketing Offer
--

-  how to identify potential customers for a targeted marketing campaign

1 - add dataset
----------------

```
aws s3api create-bucket --bucket samsa-repo --region us-west-2 --profile aws-federated
{
    "Location": "/samsa-repo"
}

# upload dataset

aws s3api put-object --bucket samsa-repo --key banking.csv --body dataset/banking.csv --profile aws-federated
{
    "ETag": "\"28c480ba0fcb436b43516c0f8e2668c7\""
}

aws s3api put-object --bucket samsa-repo --key banking-batch.csv --body dataset/banking-batch.csv --profile aws-federated
{
    "ETag": "\"da2fa7fdb61be3b7a6ea72ee37ee5048\""
}
```


2 - [create Training Datasource](http://docs.aws.amazon.com/machine-learning/latest/dg/step-2-create-a-datasource.html)
-----------------------------

- provides schema of dataset  (Numeric, Text, Categorical, or Binary)

```
{
  "version": "1.0",
  "rowId": null,
  "rowWeight": null,
  "targetAttributeName": "y",
  "dataFormat": "CSV",
  "dataFileContainsHeader": true,
  "attributes": [
    {
      "attributeName": "age",
      "attributeType": "NUMERIC"
    },
    {
      "attributeName": "job",
      "attributeType": "CATEGORICAL"
    },
    {
      "attributeName": "marital",
      "attributeType": "CATEGORICAL"
    },
    {
      "attributeName": "education",
      "attributeType": "CATEGORICAL"
    },
    {
      "attributeName": "default",
      "attributeType": "CATEGORICAL"
    },
    {
      "attributeName": "housing",
      "attributeType": "CATEGORICAL"
    },
    {
      "attributeName": "loan",
      "attributeType": "CATEGORICAL"
    },
    {
      "attributeName": "contact",
      "attributeType": "CATEGORICAL"
    },
    {
      "attributeName": "month",
      "attributeType": "CATEGORICAL"
    },
    {
      "attributeName": "day_of_week",
      "attributeType": "CATEGORICAL"
    },
    {
      "attributeName": "duration",
      "attributeType": "NUMERIC"
    },
    {
      "attributeName": "campaign",
      "attributeType": "NUMERIC"
    },
    {
      "attributeName": "pdays",
      "attributeType": "NUMERIC"
    },
    {
      "attributeName": "previous",
      "attributeType": "NUMERIC"
    },
    {
      "attributeName": "poutcome",
      "attributeType": "CATEGORICAL"
    },
    {
      "attributeName": "emp_var_rate",
      "attributeType": "NUMERIC"
    },
    {
      "attributeName": "cons_price_idx",
      "attributeType": "NUMERIC"
    },
    {
      "attributeName": "cons_conf_idx",
      "attributeType": "NUMERIC"
    },
    {
      "attributeName": "euribor3m",
      "attributeType": "NUMERIC"
    },
    {
      "attributeName": "nr_employed",
      "attributeType": "NUMERIC"
    },
    {
      "attributeName": "y",
      "attributeType": "BINARY"
    }
  ],
  "excludedAttributeNames": [
    
  ]
}
```

- provides the target attribute to predict


```
aws machinelearning create-data-source-from-s3
```

3 - [create ml-model](http://docs.aws.amazon.com/machine-learning/latest/dg/step-3-create-an-ml-model.html)
--------------------

- use Training datasource to create an ML model, 
- train the model, and then 
- evaluate the results.

_ML model is a collection of patterns that ML API finds in dataset during training.
So, ML model is used to create the predictions._

[Types of ML Models](http://docs.aws.amazon.com/machine-learning/latest/dg/types-of-ml-models.html)
---

The type of model depends on the type of target that to predict.

* [binary classification](http://docs.aws.amazon.com/machine-learning/latest/dg/binary-classification.html) (Y/N)
  eg. "Will the customer buy this product?"
  ML uses the industry-standard learning algorithm known as logistic regression.

* multiclass classification, and 
  "Is this movie a romantic comedy, documentary, or thriller?"
   ML uses the industry-standard learning algorithm known as multinomial logistic regression.

* regression
  "What will the temperature be in Seattle tomorrow?"
  ML uses the industry-standard learning algorithm known as linear regression.
  ML models for regression problems predict a numeric value.

```
aws machinelearning get-ml-model --ml-model-id ml-GvRXIrHDhLi --region us-east-1 --profile aws-federated
{
    "Status": "PENDING", 
    "Name": "ML model: banking dataset", 
    "TrainingParameters": {
        "sgd.maxPasses": "10", 
        "algorithm": "sgd", 
        "sgd.l2RegularizationAmount": "1e-6", 
        "sgd.shuffleType": "auto", 
        "sgd.maxMLModelSizeInBytes": "104857600", 
        "sgd.l1RegularizationAmount": "0.0"
    }, 
    "MLModelType": "BINARY", 
    "CreatedByIamUser": "arn:aws:sts::033814027302:assumed-role/roleName-urayagppd/urayagppd", 
    "EndpointInfo": {
        "PeakRequestsPerSecond": 0, 
        "EndpointStatus": "NONE"
    }, 
    "MLModelId": "ml-GvRXIrHDhLi", 
    "InputDataLocationS3": "s3://samsa-repo/banking.csv", 
    "LastUpdatedAt": 1494706299.462, 
    "TrainingDataSourceId": "ds-1Js4KLSdjw5", 
    "CreatedAt": 1494706299.462
}

```

ML API : 
- Splits the training datasource into two sections, one containing 70% of the data and 
one containing the remaining 30%
- Trains the ML model on the section that contains 70% of the input data
- Evaluates the model using the remaining 30% of the input data


Refs
----

http://docs.aws.amazon.com/machine-learning/latest/dg/tutorial.html

http://docs.aws.amazon.com/machine-learning/latest/dg/evaluating_models.html

