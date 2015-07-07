<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="" method="post">

  <label><span>Имя узла <span class="required">*</span></span>
    <input type="text" name="nodename" class="input-field"/>
  </label>

  <label><span>Тип modbus <span class="required">*</span></span>
    <select name="modbustype" class="select-field">
      <option value="rtu">Rtu</option>
      <option value="tcp">Tcp</option>
    </select>
  </label>

  <label><span>Порт <span class="required">*</span></span>
    <input type="text" name="port" class="input-field"/>
  </label>

  <label><span>Скорость <span class="required">*</span></span>
    <select name="baudrate" class="select-field">
      <option value="1200">1200</option>
      <option value="1800">1800</option>
      <option value="2400">2400</option>
      <option value="4800">4800</option>
      <option value="9600">9600</option>
      <option value="19200">19200</option>
      <option value="38400">38400</option>
      <option value="57600">57600</option>
      <option value="115200">115200</option>
    </select>
  </label>

  <label><span>Данные <span class="required">*</span></span>
    <select name="databits" class="select-field">
      <option value="5">5</option>
      <option value="6">6</option>
      <option value="7">7</option>
      <option value="8">8</option>
    </select>
  </label>

  <label><span>Четность <span class="required">*</span></span>
    <input type="text" name="parity" class="input-field"/>
  </label>

  <label><span>Стоп биты <span class="required">*</span></span>
    <select name="parity" class="select-field">
      <option value="0">0</option>
      <option value="1">1</option>
      <option value="1.5">1.5</option>
      <option value="2">2</option>
    </select>
  </label>

  <label><span>Повторы при ошибке <span class="required">*</span></span>
    <input type="text" name="retries" class="input-field">
  </label>

  <label><span>Время ответа <span class="required">*</span></span>
    <input type="text" name="timeout" class="input-field">
  </label>

  <label><span>Период опроса<span class="required">*</span></span>
    <input type="text" name="period" class="input-field">
  </label>
  <input type="submit" value="Сохранить"/>
</form>
</body>
</html>
