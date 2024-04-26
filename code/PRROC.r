library(PRROC)

# Illustrative example 1 with no ties
score <- c(1, 0, 1, 0, 0)
label <- c(0.9, 0.8, 0.7, 0.6, 0.5)

# Compute AUPRC
pr.1 <- pr.curve(scores.class0 = score,weights.class0= label,curve=T,rand.compute=T)
pr.1

# Construct PRC
plot(pr.1)