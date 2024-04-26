library('PerfMeas')

# Illustrative example 1 with no ties
score <- c(1, 0, 1, 0, 0)
label <- c(0.9, 0.8, 0.7, 0.6, 0.5)

# Compute AUPRC
res <- precision.at.all.recall.levels(score,label)
res
trap.rule.integral(res[[2]],res[[1]])

# Construct PRC
precision.recall.curves.plot(list(res))