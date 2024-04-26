import torch
from torcheval.metrics import BinaryAUPRC

# Illustrative example 1 with no ties
label = [1, 0, 1, 0, 0]
score = [0.9, 0.8, 0.7, 0.6, 0.5]

# Compute AUPRC
metric = BinaryAUPRC()
input = torch.tensor(score)
target = torch.tensor(label)
metric.update(input, target)
print(metric.compute())
# No visualization