import sklearn.metrics as metrics

# Illustrative example 1 with no ties
label = [1, 0, 1, 0, 0]
score = [0.9, 0.8, 0.7, 0.6, 0.5]

# Direct straight line
# Compute AUPRC
precision,recall,_ = metrics.precision_recall_curve(label,score)
print(metrics.auc(recall,precision))
# No visualization

# AP
# Compute AUPRC
print(metrics.average_precision_score(label,score))
# Construct PRC
metrics.PrecisionRecallDisplay.from_predictions(label,score)