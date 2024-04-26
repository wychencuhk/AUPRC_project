library(PRROC)

# Illustrative example 1 with no ties
label <- c(1, 0, 1, 0, 0)
score <- c(0.9, 0.8, 0.7, 0.6, 0.5)

# Compute AUPRC
pr.1 <- pr.curve(scores.class0 = score,weights.class0= label,curve=T,rand.compute=T)
pr.1

# Construct PRC with continuous expectation
plot(pr.1)

# Construct PRC with dirct straight line (without ties) or discrete expectation (with ties)
pr.2 <- pr.curve(scores.class0 = score[label==1], scores.class1 = score[label==0], curve = TRUE,rand.compute = T)
plot(pr.2)