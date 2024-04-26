library(precrec)

# Illustrative example 1 with no ties
label <- c(1, 0, 1, 0, 0)
score <- c(0.9, 0.8, 0.7, 0.6, 0.5)

# Compute AUPRC
sscurves <- evalmod(scores = score, labels = label,x_bins=1000)
sscurves

# Construct PRC
plot(sscurves)