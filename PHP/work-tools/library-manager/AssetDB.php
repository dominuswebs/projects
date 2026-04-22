<?php

class AssetDB
{
    private string $file = DB_ASSET;
    private string $file_backup = DB_ASSET . '.bak';

    public function writeDB(array $data): void
    {
        if(empty($data)) {
            throw new Exception('Data is empty.');
        }
        // create backup
        if(file_exists($this->file)) {
            copy($this->file, $this->file_backup);
        }
        
        file_put_contents($this->file, json_encode($data, JSON_PRETTY_PRINT));
    }

    public function loadDB(): array
    {
        return json_decode(file_get_contents($this->file), true);
    }
}

