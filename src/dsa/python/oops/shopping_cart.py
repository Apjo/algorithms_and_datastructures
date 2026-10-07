# link: https://algomaster.io/practice/low-level-design/design-shopping-cart
class ShoppingCart:
    # Keep all cart state internal. Use underscore-prefixed attributes
    # for the items, discount, and checkout status.
    VALID_CODE = "SAVE10"
    DISCOUNT_RATE = 0.10

    def __init__(self):
        self._cart = {}
        self._checked_out = False
        self._apply_discount = False

    def addItem(self, name: str, price: float) -> bool:
        if not self._checked_out:
            self._cart[name] = price
            return True
        return False

    def applyDiscount(self, code: str) -> bool:
        if self._apply_discount or self._checked_out or code != self.VALID_CODE:
            return False
        if code == self.VALID_CODE:
            self._apply_discount = True
        return True

    def getTotal(self) -> float:
        if not self._cart:
            return 0.0

        all_prices_tot = sum(self._cart.values())

        return (
            all_prices_tot
            if not self._apply_discount
            else all_prices_tot * (1 - self.DISCOUNT_RATE)
        )

    def checkout(self) -> bool:
        if len(self._cart) == 0 or self._checked_out:
            return False

        # self._cart.clear()
        self._checked_out = True
        return True

    def isCheckedOut(self) -> bool:
        return self._checked_out


# Your ShoppingCart object will be instantiated and called as such:
# obj = ShoppingCart()
# param_1 = obj.addItem(name, price)
# param_2 = obj.applyDiscount(code)
# param_3 = obj.getTotal()
# param_4 = obj.checkout()
# param_5 = obj.isCheckedOut()