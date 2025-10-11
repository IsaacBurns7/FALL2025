import matplotlib.pyplot as plt

n = [2**k for k in range(1,14)]
num_comp = [n for n in range(1, 14)] # replace with your program output

plt.plot(n, num_comp, marker='o')
plt.xscale("log", base=2)   # log scale to show 2^k nicely
plt.xlabel("Vector size (n)")
plt.ylabel("Comparisons")
plt.title("Binary Search Comparisons vs Vector Size")
plt.grid(True)
plt.show()
