library('PerfMeas')

# Illustrative example 1 with no ties
label <- c(1, 0, 1, 0, 0)
score <- c(0.9, 0.8, 0.7, 0.6, 0.5)

# Compute AUPRC
res <- list(precision.at.all.recall.levels(score,label))
AUPRC (res, comp.precision=TRUE)

# Construct PRC
precision.recall.curves.plot(res)