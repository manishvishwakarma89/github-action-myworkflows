name: Java Maven Matrix

on:
  workflow_calls:


jobs:
  validate:
    runs-on: ubuntu-latest

    strategy:
      fail-fast: false
      matrix:
        java-version: [ "8", "11", "17", "21" ]

    steps:
      - name: Checkout code
        uses: actions/checkout@v4

      - name: Setup JDK ${{ matrix.java-version }}
        uses: actions/setup-java@v4
        with:
          distribution: "temurin"
          java-version: ${{ matrix.java-version }}
          cache: maven

      - name: Verify Java version
        run: java -version

      - name: Build with Maven
        run: mvn clean install

      - name: Run Tests
        run: mvn test