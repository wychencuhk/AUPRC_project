import tensorflow as tf

# Illustrative example 1 with no ties
label = [1, 0, 1, 0, 0]
score = [0.9, 0.8, 0.7, 0.6, 0.5]

# Compute AUPRC
m = tf.keras.metrics.AUC(curve='PR')
m.update_state(label,score)
print(m.result().numpy())
# No visualization