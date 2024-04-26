library(ROCR)

# Illustrative example 1 with no ties
label <- c(1, 0, 1, 0, 0)
score <- c(0.9, 0.8, 0.7, 0.6, 0.5)

pred <- prediction(score, label)

# Compute AUPRC
perf <- performance(pred, "aucpr")
perf@y.values

# Construct PRC
perf <- performance(pred, "prec", "rec")
plot(perf,
     colorize=TRUE,
     lwd= 3,
     main= "PRC")